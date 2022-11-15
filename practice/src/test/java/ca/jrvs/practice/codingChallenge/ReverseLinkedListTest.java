package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import ca.jrvs.practice.codingChallenge.ReverseLinkedList.ListNode;
import java.util.List;
import org.junit.Test;

public class ReverseLinkedListTest {

  ReverseLinkedList list = new ReverseLinkedList();


  @Test
  public void reverseListIterTest(){
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ListNode reverse = list.reverseLinkedListIter(head);
    assertEquals(5, reverse.val);
    assertEquals(4, reverse.next.val);
    assertEquals(3, reverse.next.next.val);
    assertEquals(2, reverse.next.next.next.val);
    assertEquals(1, reverse.next.next.next.next.val);
  }


  @Test
  public void reverseListRecurTest(){
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ListNode reverse = list.reverseLinkedListRecur(head);
    assertEquals(5, reverse.val);
    assertEquals(4, reverse.next.val);
    assertEquals(3, reverse.next.next.val);
    assertEquals(2, reverse.next.next.next.val);
    assertEquals(1, reverse.next.next.next.next.val);
  }


}
