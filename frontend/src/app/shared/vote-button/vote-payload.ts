import { VoteType } from "./vote-type";

export interface VoteModel {
    postId: number;
    voteType: VoteType;
}