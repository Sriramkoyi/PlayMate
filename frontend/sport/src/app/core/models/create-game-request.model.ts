export interface CreateGameRequest {

  title: string;

  description: string;

  venueName: string;

  address: string;

  latitude: number;

  longitude: number;

  gameDate: string;

  startTime: string;

  endTime: string;

  entryFee: number;

  maxPlayers: number;

  sportId: number;

}