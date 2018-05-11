package com.codurance.exercise.application;

import com.codurance.exercise.domain.Content;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Social network API methods implementation unit tests
 * @author Solange U. Gasengayire
 */
@DisplayName("Social Network Unit Tests")
class SocialNetworkImplTest {

    private SocialNetworkImpl network = new SocialNetworkImpl();

    @Test
    @DisplayName("Creating and retrieving content")
    void shouldReturnCreatedContent() {
        postAliceContent();

        Set<Content> aliceContent = network.getUserContent("Alice");
        assertAll("Post a message",
                () -> assertNotNull(aliceContent),
                () -> assertFalse(aliceContent.isEmpty()),
                () -> assertEquals(1, aliceContent.size())
        );

        Object[] contentArray = aliceContent.toArray();
        Content content = (Content) contentArray[0];
        assertAll("Retrieve a message",
                () -> assertEquals(1, contentArray.length),
                () -> assertEquals("I love the weather today", content.getContent())
        );

        postBobContent();

        Set<Content> bobContent = network.getUserContent("Bob");
        Object[] bobArray = bobContent.toArray();
        assertAll("Posted messages should be retrieved in reverse order of creation",
                () -> assertEquals(2, bobArray.length),
                () -> assertEquals("Good game though.", ((Content) bobArray[0]).getContent()),
                () -> assertEquals("Damn! We lost!", ((Content) bobArray[1]).getContent())
        );
    }

    @Test
    @DisplayName("Adding a subscription")
    void shouldIncludeSubscriptionContent() {
        postAliceContent();
        postCharlieContent();
        createCharlieSubscriptionToAlice();

        Set<Content> allContent = network.getUserAggregatedContent("Charlie");
        Object[] contentArray = allContent.toArray();

        assertAll("Create a subscription and display aggregate content list",
                () -> assertNotNull(contentArray),
                () -> assertEquals(2, contentArray.length),
                () -> assertEquals("Charlie", ((Content) contentArray[0]).getOwner().getName()),
                () -> assertEquals("Alice", ((Content) contentArray[1]).getOwner().getName())
        );
    }

    @Test
    @DisplayName("Retrieving aggregated content from all subscriptions in descending order of creation time")
    void shouldIncludeAllSubscriptionContentInSortedOrder() {
        postAliceContent();
        postBobContent();
        postCharlieContent();
        createCharlieSubscriptionToAlice();
        createCharlieSubscriptionToBob();

        Set<Content> allContent = network.getUserAggregatedContent("Charlie");
        Object[] contentArray = allContent.toArray();

        LocalDateTime timestamp = LocalDateTime.now();
        for (Content content: allContent) {
            assertTrue(content.getCreationDate().isBefore(timestamp));
            timestamp = content.getCreationDate();
        }

        assertAll("Aggregated content should be display in reverse order of creation",
                () -> assertNotNull(contentArray),
                () -> assertEquals(4, contentArray.length),
                () -> assertEquals("Charlie", ((Content) contentArray[0]).getOwner().getName()),
                () -> assertEquals("Bob", ((Content) contentArray[1]).getOwner().getName()),
                () -> assertEquals("Damn! We lost!", ((Content) contentArray[2]).getContent()),
                () -> assertEquals("Alice", ((Content) contentArray[3]).getOwner().getName())
        );

    }

    /**
     * Prepare and create Alice's content
     */
    private void postAliceContent() {
        network.createContent(
                "Alice",
                "I love the weather today",
                LocalDateTime.now().minusMinutes(10)
        );
    }

    /**
     * Prepare and create Bob's content
     */
    private void postBobContent() {
        network.createContent(
                "Bob",
                "Damn! We lost!",
                LocalDateTime.now().minusMinutes(8)
        );

        network.createContent(
                "Bob",
                "Good game though.",
                LocalDateTime.now().minusMinutes(6)
        );
    }

    /**
     * Prepare and create Charlie's content
     */
    private void postCharlieContent() {
        network.createContent(
                "Charlie",
                "I'm in New York today! Anyone want to have a coffee?",
                LocalDateTime.now().minusMinutes(4)
        );
    }

    /**
     * Add Charlie to Alice's followers
     */
    private void createCharlieSubscriptionToAlice() {
        network.createSubscription(
                "Charlie",
                "Alice",
                LocalDateTime.now().minusMinutes(2)
        );
    }

    /**
     * Add Charlie to Bob's followers
     */
    private void createCharlieSubscriptionToBob() {
        network.createSubscription(
                "Charlie",
                "Bob",
                LocalDateTime.now().minusMinutes(1)
        );
    }
}
