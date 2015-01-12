package fragment.submissions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class TextUtilsTest {
	@InjectMocks
    private TextUtils textUtils;

    private String fragment1 = "original t";
    private String fragment2 = "nal text";
	private String mergedText = "original text";

    private String noOverlapFragment1 = "not";
    private String noOverlapFragment2 = "mergeable";

    @Before
    public void setUp(){
        textUtils = new TextUtils();
    }

    @Test(expected = CannotReassembleException.class)
    public void reassemble_throws_exception_for_null_fragment() throws Exception {
       textUtils.reassemble(null);
    }

    @Test
    public void reassemble_returns_empty_string_for_empty_fragment() throws Exception {
        assertEquals("", textUtils.reassemble(""));
    }

    @Test
    public void reassemble_returns_original_fragment_for_single_fragment() {
        assertEquals("single fragment", textUtils.reassemble("single fragment"));
    }

    @Test
    public void reassemble_returns_reassembled_text_for_two_fragments() {
		TextUtils textUtilsSpy = spy(textUtils);
		List<String> mergedList = new ArrayList<String>();
		mergedList.add(mergedText);
        doReturn(mergedList).when(textUtilsSpy).mergeFragments(anyListOf(String.class));
        assertEquals(mergedText, textUtilsSpy.reassemble("original ;ginal text"));
    }

	@Test
	public void reassemble_returns_reassembled_text_for_three_fragments() {
		TextUtils textUtilsSpy = spy(textUtils);

		List<String> partialMergedList = new ArrayList<String>();
		partialMergedList.add("three fra");
		partialMergedList.add(" fragments text");

		List<String> mergedList = new ArrayList<String>();
		mergedList.add("three fragments text");

        doReturn(partialMergedList).doReturn(mergedList).when(textUtilsSpy).mergeFragments(anyListOf(String.class));
		assertEquals("three fragments text", textUtilsSpy.reassemble("three fra; fragments te;ments text"));
        verify(textUtilsSpy, times(2)).mergeFragments(anyListOf(String.class));
	}

	@Test
	public void reassemble_returns_reassembled_text_for_three_fragments_with_max_overlap() {
		assertEquals("modi tempora voluptatem eius t", textUtils.reassemble("modi tem;m eius t;tempora voluptatem"));
		// Rather than return "modi tem eius tempora voluptatem"
	}

	@Test
	public void mergeFragments_returns_the_same_item_if_there_is_only_one_item(){
		List<String> singleItemList = new ArrayList<String>();
		singleItemList.add("first fragment");

        List<String> mergedFragments = textUtils.mergeFragments(singleItemList);
        assertEquals(1, mergedFragments.size());
		assertEquals("first fragment", mergedFragments.get(0));
	}

    @Test(expected = CannotReassembleException.class)
    public void mergeFragments_throws_exception_for_null_list(){
        textUtils.mergeFragments(null);
    }

    @Test(expected = CannotReassembleException.class)
    public void mergeFragments_throws_exception_for_empty_list(){
        textUtils.mergeFragments(new ArrayList<String>());
    }

	@Test
	public void mergeFragments_merges_two_mergeable_fragments(){
        TextUtils textUtilsSpy = spy(textUtils);

		List<String> twoMergeableItems = new ArrayList<String>();
        twoMergeableItems.add(fragment1);
        twoMergeableItems.add(fragment2);

        doReturn(5).when(textUtilsSpy).countOverlap(eq(fragment1), eq(fragment2));
        doReturn(mergedText).when(textUtilsSpy).mergeOverlap(eq(fragment1), eq(fragment2));

        List<String> mergedFragments = textUtilsSpy.mergeFragments(twoMergeableItems);

        verify(textUtilsSpy, times(1)).countOverlap(eq(fragment1), eq(fragment2));
        verify(textUtilsSpy, times(1)).mergeOverlap(eq(fragment1), eq(fragment2));
		assertEquals(1, mergedFragments.size());
		assertEquals(mergedText, mergedFragments.get(0));
	}

    @Test(expected = CannotReassembleException.class)
    public void mergeFragments_throws_exception_if_cannot_merge(){

        TextUtils textUtilsSpy = spy(textUtils);

        List<String> twoMergeableItems = new ArrayList<String>();
        twoMergeableItems.add(noOverlapFragment1);
        twoMergeableItems.add(noOverlapFragment2);

        doReturn(0).when(textUtilsSpy).countOverlap(eq(noOverlapFragment1), eq(noOverlapFragment2));

        textUtilsSpy.mergeFragments(twoMergeableItems);

        verify(textUtilsSpy, times(1)).countOverlap(eq(noOverlapFragment1), eq(noOverlapFragment2));
    }

    @Test
    public void countOverlap_correctly_count_overlapped_chars(){
        assertEquals(5, textUtils.countOverlap(fragment2, fragment1));
        assertEquals(5, textUtils.countOverlap(fragment1, fragment2));
    }

    @Test
    public void countOverlap_returns_zero_if_no_overlap(){
        assertEquals(0, textUtils.countOverlap(noOverlapFragment1, noOverlapFragment2));
    }

    @Test
    public void countOverlap_returns_zero_for_two_empty_string(){
        assertEquals(0, textUtils.countOverlap("", ""));
    }

    @Test
    public void countOverlap_correctly_count_overlapped_chars_fragment1_contains_fragment2(){
        assertEquals(6, textUtils.countOverlap("fragment1", "agment"));
        assertEquals(6, textUtils.countOverlap("agment", "fragment1"));
    }

    @Test
    public void mergeOverlap_correctly_merge_overlapped_fragments(){
        assertEquals(mergedText, textUtils.mergeOverlap(fragment1, fragment2));
        assertEquals(mergedText, textUtils.mergeOverlap(fragment2, fragment1));
    }

    @Test
    public void mergeOverlap_correctly_merge_identical_fragments(){
        assertEquals("original t", textUtils.mergeOverlap(fragment1, fragment1));
    }

    @Test(expected = CannotReassembleException.class)
    public void mergeOverlap_throws_exception_if_no_overlap(){
        textUtils.mergeOverlap(noOverlapFragment1, noOverlapFragment2);
    }

    @Test(expected = CannotReassembleException.class)
    public void mergeOverlap_throws_exception_when_both_fragments_are_empty(){
        textUtils.mergeOverlap("", "");
    }

    @Test(expected = CannotReassembleException.class)
    public void mergeOverlap_throws_exception_when_one_fragment_is_empty(){
        textUtils.mergeOverlap(fragment1, "");
    }
}