export interface CurrencyInfoResponse {
  id: string;
  currency: string;
  dailyTransaction: number;
  dailyTradingVolume: number;
  hourlyTradingVolume: number;
  description: string;
}
