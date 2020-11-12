import java.util.ArrayList;

public class finalScore {

    private ArrayList<winCondition> scoring;
    private ArrayList<Boolean> takeFromScoredOnly;

    public finalScore(ArrayList<winCondition> scoring, ArrayList<Boolean> takeFromScoredOnly){
        this.scoring = scoring;
        this.takeFromScoredOnly = takeFromScoredOnly;
    }

    public int[] score(ArrayList<Card> cards) {
        int[] score = new int[3];
        for (int i = 1; i < this.scoring.size(); i++) {
            int currentCondition = this.scoring.get(i).condition(cards);
            if (currentCondition > 0) {
                score[0] = this.scoring.size() - i;
                score[1] = currentCondition;
                break;
            }
        }
        if (score[0] != 0) {
            if (takeFromScoredOnly.get(Math.abs(score[0] - this.scoring.size()))) {
                score[2] = score[1];
            } else {
                score[2] = this.scoring.get(0).condition(cards);
            }
        }
        else {
        score[2] = this.scoring.get(0).condition(cards);
    }
        return score;
    }
    public static finalScore standartScoring;
    static {
        ArrayList<winCondition> standartScoringList = new ArrayList<>();
        standartScoringList.add(scoringSystems.highestCard);
        standartScoringList.add(scoringSystems.straightFlush);
        standartScoringList.add(scoringSystems.fourOfAKind);
        standartScoringList.add(scoringSystems.fullHouse);
        standartScoringList.add(scoringSystems.flush);
        standartScoringList.add(scoringSystems.straight);
        standartScoringList.add(scoringSystems.threeOfAKind);
        standartScoringList.add(scoringSystems.twoPair);
        standartScoringList.add(scoringSystems.pair);

        ArrayList<Boolean> takeFromScoredOnly = new ArrayList<>(9);
        takeFromScoredOnly.add(false);
        takeFromScoredOnly.add(true);
        takeFromScoredOnly.add(false);
        takeFromScoredOnly.add(true);
        takeFromScoredOnly.add(true);
        takeFromScoredOnly.add(true);
        takeFromScoredOnly.add(false);
        takeFromScoredOnly.add(false);
        takeFromScoredOnly.add(false);
        standartScoring = new finalScore(standartScoringList, takeFromScoredOnly);
    }
}
