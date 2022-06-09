import { environment } from "src/environments/environment";

export class Constants {
    static API_ENDPOINT = environment.baseUrl;
    // should always be an odd number greater than 1
    static EVENT_THRESHOLD = 3;
    static EVENTS_VALUE_KEY = "events";
}
