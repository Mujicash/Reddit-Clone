export interface LoginResponse {
    authenticationToken: string;
    expireAt: Date;
    refreshToken: string;
    username: string;
}