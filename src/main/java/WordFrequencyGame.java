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

                String[] words = sentence.split(SPLIT_REGEX);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfos.add(wordInfo);
                }

                Map<String, List<WordInfo>> wordMap =getListMap(wordInfos);

                List<WordInfo> tempWordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : wordMap.entrySet()) {
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    tempWordInfos.add(wordInfo);
                }
                wordInfos = tempWordInfos;

                wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (WordInfo wordInfo : wordInfos) {
                    String s = wordInfo.getValue() + SPACE +wordInfo.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
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
