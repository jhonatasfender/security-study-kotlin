#!/usr/bin/env node

import axios from 'axios';
import chalk from 'chalk';
import ora from 'ora';
import { faker } from '@faker-js/faker';

const BASE_URL = "http://localhost:8080/api";
const AUTH_ENDPOINTS = {
    register: '/auth/register',
    login: '/auth/login', 
    userInfo: '/auth/me',
    refresh: '/auth/refresh',
    logout: '/auth/logout'
};

class ApiClientFactory {
    static create(token = null) {
        const headers = {
            'Content-Type': 'application/json',
            ...(token && { 'Authorization': `Bearer ${token}` })
        };
        
        return axios.create({
            baseURL: BASE_URL,
            headers
        });
    }
}

class Utils {
    static printResponse(data) {
        console.log(chalk.cyan('Response:'));
        console.log(JSON.stringify(data, null, 2));
    }

    static generateRandomUser() {
        return {
            username: faker.internet.userName().toLowerCase(),
            password: faker.internet.password({ length: 12, memorable: true }),
            roles: faker.helpers.arrayElements(['USER', 'ADMIN'], { min: 1, max: 2 })
        };
    }

    static handleError(error, message) {
        console.error(`Error: ${message}`);
        if (error.response?.data) {
            console.error('Response:', JSON.stringify(error.response.data, null, 2));
        }
        return false;
    }
}

class AuthService {
    constructor(api, spinner) {
        this.api = api;
        this.spinner = spinner;
    }

    async register(user) {
        try {
            this.spinner.start(`Registering new user ${user.username}...`);
            await this.api.post(AUTH_ENDPOINTS.register, user);
            this.spinner.succeed('Registration successful!');
            return true;
        } catch (error) {
            this.spinner.fail('Registration failed!');
            return Utils.handleError(error, 'Registration failed');
        }
    }

    async login(credentials) {
        try {
            this.spinner.start('Performing login...');
            const response = await this.api.post(AUTH_ENDPOINTS.login, credentials);
            this.spinner.succeed('Login successful!');
            return response.data;
        } catch (error) {
            this.spinner.fail('Login failed!');
            return Utils.handleError(error, 'Login failed');
        }
    }

    async getUserInfo() {
        try {
            this.spinner.start('Testing user info endpoint...');
            const response = await this.api.get(AUTH_ENDPOINTS.userInfo);
            this.spinner.succeed('User info retrieved successfully!');
            Utils.printResponse(response.data);
            return true;
        } catch (error) {
            this.spinner.fail('User info retrieval failed!');
            return Utils.handleError(error, 'User info retrieval failed');
        }
    }

    async refreshToken(refreshToken) {
        try {
            this.spinner.start('Testing refresh token...');
            const response = await this.api.post(AUTH_ENDPOINTS.refresh, { refreshToken });
            this.spinner.succeed('Refresh token successful!');
            Utils.printResponse(response.data);
            return true;
        } catch (error) {
            this.spinner.fail('Refresh token failed!');
            return Utils.handleError(error, 'Refresh token failed');
        }
    }

    async logout() {
        try {
            this.spinner.start('Testing logout...');
            await this.api.post(AUTH_ENDPOINTS.logout);
            this.spinner.succeed('Logout successful!');
            return true;
        } catch (error) {
            this.spinner.fail('Logout failed!');
            return Utils.handleError(error, 'Logout failed');
        }
    }
}

class AuthTestFlow {
    constructor() {
        this.spinner = ora();
    }

    printTokenInfo(user, tokens) {
        const { accessToken, refreshToken, expiresAt } = tokens;
        
        console.log('\nUser Information:');
        console.log('Username:', user.username);
        console.log('Roles:', user.roles.join(', '));
        console.log('\nToken Information:');
        console.log('Access Token:', accessToken);
        console.log('Refresh Token:', refreshToken);
        console.log('Expires At:', expiresAt);
    }

    async run() {
        try {
            const user = Utils.generateRandomUser();
            const unauthenticatedApi = ApiClientFactory.create();
            const authService = new AuthService(unauthenticatedApi, this.spinner);

            if (!await authService.register(user)) return;
            
            const tokens = await authService.login(user);
            if (!tokens) return;

            this.printTokenInfo(user, tokens);

            const authenticatedApi = ApiClientFactory.create(tokens.accessToken);
            const authenticatedAuthService = new AuthService(authenticatedApi, this.spinner);

            await authenticatedAuthService.getUserInfo();
            await authenticatedAuthService.refreshToken(tokens.refreshToken);
            await authenticatedAuthService.logout();

        } catch (error) {
            this.spinner.fail(`An error occurred: ${error.message}`);
            Utils.handleError(error, 'Unexpected error');
            process.exit(1);
        }
    }
}

new AuthTestFlow().run();
