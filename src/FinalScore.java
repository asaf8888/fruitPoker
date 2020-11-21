import java.util.ArrayList;

public class FinalScore {

    private ArrayList<winCondition> scoring;
    private ArrayList<Boolean> takeFromScoredOnly;

    public FinalScore(ArrayList<winCondition> scoring, ArrayList<Boolean> takeFromScoredOnly){
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
    public static FinalScore standartScoring;
    static {
        ArrayList<winCondition> standartScoringList = new ArrayList<>();
        standartScoringList.add(ScoringSystems.highestCard);
        standartScoringList.add(ScoringSystems.straightFlush);
        standartScoringList.add(ScoringSystems.fourOfAKind);
        standartScoringList.add(ScoringSystems.fullHouse);
        standartScoringList.add(ScoringSystems.flush);
        standartScoringList.add(ScoringSystems.straight);
        standartScoringList.add(ScoringSystems.threeOfAKind);
        standartScoringList.add(ScoringSystems.twoPair);
        standartScoringList.add(ScoringSystems.pair);

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
        standartScoring = new FinalScore(standartScoringList, takeFromScoredOnly);
    }
}
