package ca.jrvs.practice.codingChallenge;

public class ReverseLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

  /**
   * Big O notation: O(n)
   *
   * Justification: Iterate and loop through the LinkedList once by changing pointer of each node
   *
   * @param head
   * @return
   */
  public ListNode reverseLinkedListIter(ListNode head) {

      if(head==null){
        return head;
      }

      ListNode prev = null;
      ListNode present = head;

      while(present != null){
        ListNode nextNode = present.next;
        present.next = prev;
        prev = present;
        present = nextNode;
      }

      return prev;
  }

  /**
   * Big O notation: O(n)
   *
   * Justification: Use recursion to access each node.
   *
   * @param head
   * @return
   */
  public ListNode reverseLinkedListRecur(ListNode head) {
      if(head==null||head.next==null){
        return head;
      }

      ListNode newHead = reverseLinkedListRecur(head.next);

      head.next.next = head;
      head.next = null;

      return newHead;
  }

}
