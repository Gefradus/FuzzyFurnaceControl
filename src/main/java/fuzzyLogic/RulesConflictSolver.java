package fuzzyLogic;

import java.util.ArrayList;
import java.util.LinkedList;

public class RulesConflictSolver {

    public static LinkedList<Rule> solveConflict(LinkedList<Rule> ruleList)
    {
        int[] conflictVeryLow = isConflict(ruleList, 2,6);
        int[] conflictLow = isConflict(ruleList, 1,5);
        int[] conflictMedium = isConflict(ruleList, 7,8);
        int[] conflictLittleHigh = isConflict(ruleList, 7,12);
        int[] conflictHigh = isConflict(ruleList, 11,15);
        int[] conflictVeryHigh = isConflict(ruleList, 10,14);

        LinkedList<Rule> newRules = new LinkedList<>();
        ArrayList<Integer> ruleNumbersToDelete = new ArrayList<>();
        ruleddd(conflictVeryLow, ruleList, newRules, ruleNumbersToDelete);
        ruleddd(conflictLow, ruleList, newRules, ruleNumbersToDelete);
        ruleddd(conflictMedium, ruleList, newRules, ruleNumbersToDelete);
        ruleddd(conflictLittleHigh, ruleList, newRules, ruleNumbersToDelete);
        ruleddd(conflictHigh, ruleList, newRules, ruleNumbersToDelete);
        ruleddd(conflictVeryHigh, ruleList, newRules, ruleNumbersToDelete);

        for (Integer ruleNumber : ruleNumbersToDelete) {
            ruleList.removeIf(rule -> rule.getNumber() == ruleNumber);
        }

        ruleList.addAll(newRules);
        return ruleList;
    }


    //zwraca indeksy regul, w ktorych jest konflikt
    private static int[] isConflict(LinkedList<Rule> ruleList, int r1test, int r2test){
        int R1index = -1;
        int R2index = -1;

        for(int i=0; i < ruleList.size(); i++){
            if(ruleList.get(i).getNumber() == r1test){
                R1index = i;
            }
            if(ruleList.get(i).getNumber() == r2test){
                R2index = i;
            }
        }
        if(R1index!=-1 && R2index!=-1){
            return new int[] {R1index, R2index};
        }
        else return null;
    }

    private static void ruleddd(int[] conflictArray, LinkedList<Rule> ruleList, LinkedList<Rule> newRules, ArrayList<Integer> ruleNumbersToDelete)
    {
        if(conflictArray != null) {
            newRules.add(chooseMethodWithMoreIgnite(ruleList.get(conflictArray[0]), ruleList.get(conflictArray[1])));
            ruleNumbersToDelete.add(ruleList.get(conflictArray[0]).getNumber());
            ruleNumbersToDelete.add(ruleList.get(conflictArray[1]).getNumber());
        }
    }

    //zwraca regule o wiekszym zapłonie
    private static Rule chooseMethodWithMoreIgnite(Rule rule1, Rule rule2){
        return (rule1.getIgnition() > rule2.getIgnition()) ? rule1 : rule2;
    }
}
