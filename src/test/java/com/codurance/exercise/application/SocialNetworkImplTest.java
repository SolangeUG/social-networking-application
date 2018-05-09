package com.codurance.exercise.application;

import com.codurance.exercise.wrapper.ContentWrapper;
import com.codurance.exercise.wrapper.SubscriptionWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Social network API methods implementation unit tests
 * @author Solange U. Gasengayire
 */
@DisplayName("Social Network")
class SocialNetworkImplTest {

    private SocialNetwork network = new SocialNetworkImpl();

    @Test
    @DisplayName("Creating and retrieving content")
    void shouldReturnCreatedContent() {
        postAliceContent();

        Map<LocalDateTime, ContentWrapper> content = network.getContent("Alice");
        Object[] contentArray = content.values().toArray();

        assertNotNull(content);
        assertFalse(content.isEmpty());
        assertNotNull(contentArray);
        assertEquals(1, contentArray.length);

        ContentWrapper wrapper = (ContentWrapper) contentArray[0];
        assertEquals("I love the weather today", wrapper.getContent());
    }

    @Test
    @DisplayName("Adding a subscription")
    void shouldIncludeSubscriptionContent() {
        postAliceContent();
        postBobContent();
        postCharlieContent();
        createCharlieSubscriptionToAlice();

        Map<LocalDateTime, ContentWrapper> allContent = network.getAllContent("Charlie");
        Object[] contentArray = allContent.values().toArray();

        assertNotNull(contentArray);
        assertEquals(2, contentArray.length);

        ContentWrapper wrapper = (ContentWrapper) contentArray[0];
        assertEquals("Charlie", wrapper.getOwner());

        wrapper = (ContentWrapper) contentArray[1];
        assertEquals("Alice", wrapper.getOwner());
    }

    @Test
    @DisplayName("Retrieving aggregated content from all subscriptions in descending order of creation time")
    void shouldIncludeAllSubscriptionContentInSortedOrder() {
        postAliceContent();
        postBobContent();
        postCharlieContent();
        createCharlieSubscriptionToAlice();
        createCharlieSubscriptionToBob();

        Map<LocalDateTime, ContentWrapper> allContent = network.getAllContent("Charlie");
        Object[] contentArray = allContent.values().toArray();

        assertNotNull(contentArray);
        assertEquals(4, contentArray.length);

        LocalDateTime timestamp = LocalDateTime.now();
        for (Map.Entry<LocalDateTime, ContentWrapper> set: allContent.entrySet()) {
            assertTrue(set.getKey().isBefore(timestamp));
            timestamp = set.getKey();
        }

        ContentWrapper wrapper = (ContentWrapper) contentArray[0];
        assertEquals("Charlie", wrapper.getOwner());

        wrapper = (ContentWrapper) contentArray[1];
        assertEquals("Bob", wrapper.getOwner());

        wrapper = (ContentWrapper) contentArray[2];
        assertEquals("Damn! We lost!", wrapper.getContent());

        wrapper = (ContentWrapper) contentArray[3];
        assertEquals("Alice", wrapper.getOwner());
    }



    /**
     * Prepare and create Alice's content
     */
    private void postAliceContent() {
        ContentWrapper content = new ContentWrapper(
                "Alice",
                "I love the weather today",
                LocalDateTime.now().minusMinutes(10)
        );
        network.createContent(content);
    }

    /**
     * Prepare and create Bob's content
     */
    private void postBobContent() {
        ContentWrapper content = new ContentWrapper(
                "Bob",
                "Damn! We lost!",
                LocalDateTime.now().minusMinutes(8)
        );
        network.createContent(content);

        content = new ContentWrapper(
                "Bob",
                "Good game though.",
                LocalDateTime.now().minusMinutes(6)
        );
        network.createContent(content);
    }

    /**
     * Prepare and create Charlie's content
     */
    private void postCharlieContent() {
        ContentWrapper content = new ContentWrapper(
                "Charlie",
                "I'm in New York today! Anyone want to have a coffee?",
                LocalDateTime.now().minusMinutes(4)
        );
        network.createContent(content);
    }

    /**
     * Add Charlie to Alice's followers
     */
    private void createCharlieSubscriptionToAlice() {
        SubscriptionWrapper subscription = new SubscriptionWrapper(
                "Charlie",
                "Alice",
                LocalDateTime.now().minusMinutes(2)
        );
        network.createSubscription(subscription);
    }

    /**
     * Add Charlie to Bob's followers
     */
    private void createCharlieSubscriptionToBob() {
        SubscriptionWrapper subscription = new SubscriptionWrapper(
                "Charlie",
                "Bob",
                LocalDateTime.now().minusMinutes(1)
        );
        network.createSubscription(subscription);
    }
}
