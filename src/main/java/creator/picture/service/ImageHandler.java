package creator.picture.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ImageHandler {

    List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void resetURLList() {
        urls.clear();
    }

    public void getImageURL(String stringFromClipboard) {
        List<Character> myList = new ArrayList<Character>();
        char[] dataToTrim = stringFromClipboard.toCharArray();
        char[] img = "<img src=\"".toCharArray();
        char endTag = '"';

        for (Character c : dataToTrim) {
            myList.add(c);
        }
        List<Integer> indexes = searchSubString(dataToTrim, img);

//        System.out.println(indexes);

        for (Integer index : indexes) {
            List<Character> characters = new ArrayList<Character>();
            for (int i = index + 10; i < myList.size(); i++) {
                if (myList.get(i) == endTag) {
                    break;
                }
                characters.add(dataToTrim[i]);
            }
            StringBuilder builder = new StringBuilder(characters.size());
            for (Character ch : characters) {
                builder.append(ch);
            }
            urls.add(builder.toString());
        }

//        System.out.println(urls);

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