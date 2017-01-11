public class NumberCountCalculator {

    public int calculateNumberCount(int inputNumber, int findRange) {
        //1의 자리에 몇번 등장하는지 새는법 10이 커질때 마다 1번씩 등장하므로 나누기 10을 하고 1을 곱하면된다 10으로 나눈 나머지가 8보다 큰 경우 1을 더해주면된다.
        //10의 자리에 몇번 등장하는지 새는법 100이 커질때 마다 10번씩 등장하므로  나누기 100을 하고 10을 곱하면된다. 100으로 나눈 나머지가 8보다 큰 경우 10을 더해주면 된다 8인경우 1의자리만큼..
        //100의 자리에 몇번 등장하는지 새는법 1000이 커질때 마다 100번씩 등장하므로 나누기 1000을 하고 100을 곱하면 된다. 1000으로 나눈 나머지가 8보다 큰 경우 이런 방법으로 가면 된다. 8인경우
        int everyFewTime = 10;
        int appearanceCount = 1;

        int count = 0;

        while (true) {
            count += (findRange / everyFewTime) * appearanceCount;

            if ((findRange%everyFewTime)/appearanceCount == inputNumber) {
                if(findRange <= appearanceCount) {
                    System.out.println(findRange);
                    System.out.println(appearanceCount);
                }
                count += findRange%appearanceCount+1;
            } else if ((findRange%everyFewTime)/appearanceCount >inputNumber)  {
                count += appearanceCount;
            }

            if (findRange / everyFewTime <= 0) {
                break;
            }

            everyFewTime *= 10;
            appearanceCount *= 10;
        }

        //0 인경우
        if (inputNumber == 0 ) {
            count -= appearanceCount;
        }

        return count;
    }
}
