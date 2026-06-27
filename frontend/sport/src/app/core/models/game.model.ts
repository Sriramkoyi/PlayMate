export interface Game {

  id: number;

  title: string;

  description: string;

  sportName: string;

  hostName: string;

  venueName: string;

  address: string;

  gameDate: string;

  startTime: string;

  endTime: string;

  entryFee: number;

  currentPlayers: number;

  maxPlayers: number;

  status: string;

  distance?: number;

}