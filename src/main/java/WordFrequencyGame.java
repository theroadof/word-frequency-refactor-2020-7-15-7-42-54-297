import java.util.*;

public class WordFrequencyGame {

    private static final String SPLIT_REGEX = "\\s+";
    private static final String LINE_BREAK = "\n";
    private static final String ERROR_MESSAGE = "Calculate Error";
    private static final String SPACE = " ";

    public String getResult(String sentence) {


        if (sentence.split(SPLIT_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordInfo> wordInfos = calculateWordInfoCounts(sentence);
                wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
                return printResultMessage(wordInfos);
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
    }

    private List<WordInfo> calculateWordInfoCounts(String sentence) {
        List<WordInfo> wordInfos = new ArrayList<>();
        List<String> words = Arrays.asList(sentence.split(SPLIT_REGEX));
        for (String unitWord : new HashSet<>(words)) {
            int count = (int) words.stream().filter(unitWord::equals).count();
            wordInfos.add(new WordInfo(unitWord, count));
        }
        return wordInfos;
    }

    private String printResultMessage(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(LINE_BREAK);
        for (WordInfo wordInfo : wordInfos) {
            String s = wordInfo.getValue() + SPACE + wordInfo.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

}
