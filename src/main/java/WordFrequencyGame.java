import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPLIT_REGEX = "\\s+";
    private static final String LINE_BREAK = "\n";
    private static final String ERROR_MESSAGE = "Calculate Error";
    private static final String SPACE = " ";

    public String getResult(String sentence) {


        if (sentence.split(SPLIT_REGEX).length==1) {
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
        Map<String, List<WordInfo>> wordMap = getWordMap(sentence);
        List<WordInfo> wordInfos;

        List<WordInfo> tempWordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordMap.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            tempWordInfos.add(wordInfo);
        }
        wordInfos = tempWordInfos;
        return wordInfos;
    }

    private String printResultMessage(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(LINE_BREAK);
        for (WordInfo wordInfo : wordInfos) {
            String s = wordInfo.getValue() + SPACE +wordInfo.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getWordMap(String sentence) {
        String[] words = sentence.split(SPLIT_REGEX);

        List<WordInfo> wordInfos = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfos.add(wordInfo);
        }

        return getListMap(wordInfos);
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfos) {
        Map<String, List<WordInfo>> wordInfoMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfos){
            if (!wordInfoMap.containsKey(wordInfo.getValue())) {
                List<WordInfo> tempWordInfos = new ArrayList<>();
                tempWordInfos.add(wordInfo);
                wordInfoMap.put(wordInfo.getValue(), tempWordInfos);
            }
            else {
                wordInfoMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordInfoMap;
    }
}
