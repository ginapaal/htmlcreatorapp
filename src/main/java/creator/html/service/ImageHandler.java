package creator.html.service;

import creator.html.model.HTMLUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ImageHandler {

    Set<HTMLUrl> urls = new HashSet<>();

    public Set<HTMLUrl> getUrls() {
        return urls;
    }

    public void resetURLList() {
        urls.clear();
    }

    public void getImageURL(String stringFromClipboard) {
        List<Character> myList = new ArrayList<Character>();
        char[] dataToTrim = stringFromClipboard.toCharArray();
        char[] img = "<img".toCharArray();
        char[] src = "src=\"".toCharArray();
        char endTag = '>';

        for (Character c : dataToTrim) {
            myList.add(c);
        }
        List<Integer> indexes = searchSubString(dataToTrim, img);

        for (Integer index : indexes) {
            List<Character> characters = new ArrayList<Character>();

            for (int i = index; i < myList.size(); i++) {
                if (myList.get(i) == endTag) {
                    break;
                }
                characters.add(dataToTrim[i]);
            }

            StringBuilder builder = new StringBuilder(characters.size());
            for (Character ch : characters) {
                builder.append(ch);
            }
            String tagString = builder.toString();
            char[] tagArray = tagString.toCharArray();
            List<Integer> srcIndexes = searchSubString(tagArray, src);
            List<Character> urlCharList = new ArrayList<>();

            for (int i = srcIndexes.get(0) + 5; i < characters.size(); i++) {
                if (characters.get(i) == '"') {
                    break;
                }

                urlCharList.add(characters.get(i));
            }

            StringBuilder buildUrl = new StringBuilder(urlCharList.size());
            for (Character character: urlCharList) {
                buildUrl.append(character);
            }
            System.out.println(buildUrl.toString());
            HTMLUrl url = new HTMLUrl();
            url.setUrl(buildUrl.toString());
            urls.add(url);
        }
    }

    public int[] preProcessPattern(char[] ptrn) {
        int i = 0, j = -1;
        int ptrnLen = ptrn.length;
        int[] b = new int[ptrnLen + 1];

        b[i] = j;
        while (i < ptrnLen) {
            while (j >= 0 && ptrn[i] != ptrn[j]) {
                j = b[j];
            }
            i++;
            j++;
            b[i] = j;
        }
        return b;
    }

    public List<Integer> searchSubString(char[] text, char[] ptrn) {
        int i = 0, j = 0;
        // pattern and text lengths
        int ptrnLen = ptrn.length;
        int txtLen = text.length;

        // initialize new array and preprocess the pattern
        int[] b = preProcessPattern(ptrn);

        List<Integer> indexes = new ArrayList<Integer>();
        while (i < txtLen) {
            while (j >= 0 && text[i] != ptrn[j]) {
                j = b[j];
            }
            i++;
            j++;

            // a match is found
            if (j == ptrnLen) {
                j = b[j];
//                System.out.println("found substring at index:" + (i - ptrnLen));
                indexes.add(i - ptrnLen);
            }
        }
        return indexes;
    }
}