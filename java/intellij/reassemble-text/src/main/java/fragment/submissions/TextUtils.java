package fragment.submissions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextUtils {
    public String reassemble(String textFragments) {
        if(textFragments == null) { throw new CannotReassembleException("Fragments to reassemble cannot be null");}
        String[] fragmentsArray = textFragments.split(";");
        if(fragmentsArray.length == 1) { return textFragments; }

        List<String> fragments = new ArrayList<String>(Arrays.asList(fragmentsArray));

        List<String> mergedFragments = mergeFragments(fragments);

        if(mergedFragments.size() > 1) {
            StringBuilder text = new StringBuilder();
            for (String fragment : mergedFragments) {
                text.append(fragment).append(";");
            }
            return reassemble(text.toString());
        }

        return mergedFragments.get(0);
    }

    protected List<String> mergeFragments(List<String> fragments) {
        if(fragments == null || fragments.size() == 0) throw new CannotReassembleException("Fragments to merge cannot be null or empty");
        if(fragments.size() == 1) return fragments;
        int maxOverlap = 0;
        int mergePosition1 = -1;
        int mergePosition2 = -1;
		for (int i = 0; i < fragments.size(); i++) {
            for (int j = 0; j < fragments.size(); j++) {
                if (i < j) {

                    int currentOverlap = countOverlap(fragments.get(i), fragments.get(j));
                    if (currentOverlap > maxOverlap) {
                        maxOverlap = currentOverlap;
                        mergePosition1 = i;
                        mergePosition2 = j;
                    }
                }
            }
        }

        // Cannot find any two fragments to merge, throw exception
        if (maxOverlap == 0) { throw new CannotReassembleException("Cannot find any two fragments to merge"); }

        fragments.set(mergePosition1, mergeOverlap(fragments.get(mergePosition1), fragments.get(mergePosition2)));
        fragments.remove(mergePosition2);

		return fragments;
    }

    protected String mergeOverlap(String fragment1, String fragment2) {
		String overlap = findOverlap(fragment1, fragment2).getMergedString();
		if (overlap.length() == 0) {
			throw new CannotReassembleException("Something is wrong. Cannot merge overlap");
		} else {
			return overlap;
		}
    }

    protected int countOverlap(String fragment1, String fragment2) {
        return findOverlap(fragment1, fragment2).getOverlappedString().length();
    }

    private TextOverlap findOverlap(String fragment1, String fragment2) {
		TextOverlap textOverlap = new TextOverlap();
        if(fragment1.equals("") || fragment2.equals((""))) {
			textOverlap.setMergedString("");
			textOverlap.setOverlappedString("");
            return textOverlap;
        }

        int fragment1Count = fragment1.length();
        int fragment2Count = fragment2.length();
        if (fragment1.contains(fragment2)) {
            textOverlap.setMergedString(fragment1);
			textOverlap.setOverlappedString(fragment2);
			return textOverlap;
        }

        if (fragment2.contains(fragment1)) {
			textOverlap.setMergedString(fragment2);
			textOverlap.setOverlappedString(fragment1);
			return textOverlap;
        }

        for (int i = 1; i < fragment2Count; i++) {
            String tailSubstring = fragment2.substring(i);
            if (fragment1.startsWith(tailSubstring) && (textOverlap.getOverlappedString().length() < tailSubstring.length())) {
                textOverlap.setOverlappedString(tailSubstring);
				textOverlap.setMergedString(fragment2.substring(0, i) + fragment1);
            }
        }

        for (int i = 1; i < fragment1Count; i++) {
            String tailSubstring = fragment1.substring(i);
            if (fragment2.startsWith(tailSubstring) && (textOverlap.getOverlappedString().length() < tailSubstring.length())) {
				textOverlap.setOverlappedString(tailSubstring);
				textOverlap.setMergedString(fragment1.substring(0, i) + fragment2);
            }
        }
        return textOverlap;
    }
}
