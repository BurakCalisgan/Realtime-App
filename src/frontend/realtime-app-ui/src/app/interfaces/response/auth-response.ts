export interface AuthResponse {
  isSuccess: true;
  message: string;
  token: string;
  id: number;
  email: string;
  roles: [];
}
