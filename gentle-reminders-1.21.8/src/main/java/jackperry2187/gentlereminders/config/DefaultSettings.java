package jackperry2187.gentlereminders.config;

import jackperry2187.gentlereminders.util.Message;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jackperry2187.gentlereminders.util.Message.generateSimpleMessage;
import static jackperry2187.gentlereminders.util.Message.generateUniqueMessage;

@Environment(value = EnvType.CLIENT)
public class DefaultSettings {
    public static final int configVersion = 5;
    public static final boolean enabled = true;
    public static final String displayStyle = "default";
    public static final String customBGTexture = "minecraft:dark_oak_planks";
    public static final String customBorderTexture = "minecraft:amethyst_block";
    public static final boolean customIncludeIcon = true;
    public static final int timeBetweenMessages = 10; // in minutes
    public static final String defaultTitle = "Gentle Reminder";
    public static final Formatting defaultTitleColor = Formatting.DARK_AQUA;
    public static final Formatting defaultMessageColor = Formatting.AQUA;

    private static final Message initialMessage = new Message(
            MutableText.of(Text.of("Gentle Reminders Enabled").getContent()).formatted(defaultTitleColor),
            MutableText.of(Text.of("Gentle Reminders is enabled. You will receive mindful messages every " + timeBetweenMessages + " minutes.").getContent()).formatted(defaultMessageColor),
            true
    );

    public static final List<Message> defaultMessages = new ArrayList<>(Arrays.asList(
            initialMessage,
            generateSimpleMessage("Remember to take a break and stretch your legs!"),
            generateSimpleMessage("Now would be a good time to drink some water!"),
            generateSimpleMessage("Time to take a deep breath and relax!"),
            generateSimpleMessage("Make sure to look away from your screen every once in a while!"),
            generateSimpleMessage("Check your posture - sit up nice and tall!"),
            generateSimpleMessage("A healthy snack can boost your energy - grab one if you can!"),
            generateSimpleMessage("Looking at something far away can be useful every once and a while - try it now!"),
            generateSimpleMessage("Smile! It's good for you!"),
            generateSimpleMessage("Sometimes it's good to take a moment to appreciate the little things!"),
            generateSimpleMessage("Roll your shoulders back and down to relieve tension!"),
            generateSimpleMessage("Take a moment to refocus and recenter yourself - what's your next goal?"),
            generateSimpleMessage("Unclench your jaw and relax your face - you might be holding tension there!"),
            generateSimpleMessage("Now would be a good time to refill your water bottle!"),
            generateSimpleMessage("Stand tall and take a deep breath - you're doing great!"),
            generateSimpleMessage("A few ankle rolls can help keep your feet happy!"),
            generateSimpleMessage("Enjoy a moment to reflect on your progress - you've come so far!"),
            generateSimpleMessage("Time to ask yourself - are you staying hydrated?"),
            generateSimpleMessage("Take a moment to appreciate the beauty around you - it's all around!"),
            generateSimpleMessage("Feel your feet on the ground - stay rooted!"),
            generateSimpleMessage("Give yourself a moment to stretch and relax!"),
            generateSimpleMessage("Think of one thing you’re grateful for right now!"),
            generateSimpleMessage("Make sure you're sitting comfortably and relaxed!"),
            generateSimpleMessage("Roll your neck gently to release any tension."),
            generateSimpleMessage("Take a moment to stretch your arms and relax your shoulders."),
            generateSimpleMessage("Hydrate - a small sip can make a big difference!"),
            generateSimpleMessage("Notice how you're sitting - feel free to adjust!"),
            generateSimpleMessage("Celebrate a small win - you’re making progress!"),
            generateSimpleMessage("Inhale deeply - fill your lungs with positivity!"),
            generateSimpleMessage("Remember - it's okay to go at your own pace."),
            generateSimpleMessage("Feel proud of the effort you're putting in!"),
            generateSimpleMessage("It's a great time to pause and clear your mind."),
            generateSimpleMessage("Stand tall and own your achievements!"),
            generateSimpleMessage("You’re doing wonderfully - celebrate that!"),
            generateSimpleMessage("You’re on a great path - keep going!"),
            generateUniqueMessage("You're doing great!", "Keep up the good work!", true, Formatting.GREEN, Formatting.DARK_GREEN),
            generateUniqueMessage("You're amazing!", "Don't forget it!", true, Formatting.LIGHT_PURPLE, Formatting.DARK_PURPLE),
            generateUniqueMessage("Have a wonderful day!", "You deserve it!", true, Formatting.BLUE, Formatting.DARK_BLUE),
            generateUniqueMessage("Take on the world!", "You're unstoppable!", true, Formatting.RED, Formatting.DARK_RED),
            generateUniqueMessage("Shine bright today!", "You're a star!", true, Formatting.YELLOW, Formatting.GOLD),
            generateUniqueMessage("Embrace the journey!", "Every moment counts!", true, Formatting.BLUE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("You're a champion!", "Keep up the good work!", true, Formatting.RED, Formatting.DARK_RED),
            generateUniqueMessage("You're a rockstar!", "You're doing amazing!", true, Formatting.RED, Formatting.GREEN),
            generateUniqueMessage("Feel the flow of progress!", "Trust the process!", true, Formatting.AQUA, Formatting.BLUE),
            generateUniqueMessage("Look at how talented you are!", "You've created something amazing!", true, Formatting.LIGHT_PURPLE, Formatting.DARK_PURPLE),
            generateUniqueMessage("You're a creative genius!", "Your ideas are incredible!", true, Formatting.GREEN, Formatting.GOLD),
            generateUniqueMessage("Believe in your magic!", "You're capable of anything!", true, Formatting.DARK_PURPLE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("You're a work of art!", "You're beautiful inside and out!", true, Formatting.BLUE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("You are enough!", "Just as you are.", true, Formatting.WHITE, Formatting.GRAY),
            generateUniqueMessage("Stay true to you!", "You're on the right path!", true, Formatting.DARK_GRAY, Formatting.WHITE),
            generateUniqueMessage("Your hard work is paying off!", "Keep it up!", true, Formatting.DARK_GREEN, Formatting.GREEN),
            generateUniqueMessage("You're a ray of sunshine!", "Brightening the world!", true, Formatting.YELLOW, Formatting.GOLD),
            generateUniqueMessage("You're a breath of fresh air!", "Refreshing the world!", true, Formatting.AQUA, Formatting.BLUE),
            generateUniqueMessage("You're a guiding light!", "Illuminating the world!", true, Formatting.DARK_PURPLE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("You're a force of nature!", "Changing the world!", true, Formatting.GREEN, Formatting.RED),
            generateUniqueMessage("This is a Unique Message", "And you're a wonderfully unique person!", true, Formatting.GOLD, Formatting.YELLOW),
            generateUniqueMessage("Believe in Yourself!", "You’re stronger than you think!", true, Formatting.DARK_BLUE, Formatting.AQUA),
            generateUniqueMessage("You’re on the Right Path", "Trust your journey.", true, Formatting.GOLD, Formatting.DARK_PURPLE),
            generateUniqueMessage("Embrace Your Potential", "You’re capable of amazing things!", true, Formatting.DARK_PURPLE, Formatting.GREEN),
            generateUniqueMessage("Shine On!", "Your inner light is brilliant!", true, Formatting.YELLOW, Formatting.AQUA),
            generateUniqueMessage("Feel the Positivity", "Let it fill your heart.", true, Formatting.AQUA, Formatting.GREEN),
            generateUniqueMessage("Breathe in Strength", "You are resilient!", true, Formatting.DARK_AQUA, Formatting.RED),
            generateUniqueMessage("You’ve Got This!", "Trust your instincts.", true, Formatting.DARK_RED, Formatting.GOLD),
            generateUniqueMessage("Radiate Kindness", "You make the world brighter.", true, Formatting.LIGHT_PURPLE, Formatting.GREEN),
            generateUniqueMessage("Celebrate Progress", "Every step counts!", true, Formatting.GREEN, Formatting.BLUE),
            generateUniqueMessage("Keep the Faith", "Good things are coming.", true, Formatting.DARK_GREEN, Formatting.DARK_BLUE),
            generateUniqueMessage("Spark Your Creativity", "Your ideas are brilliant!", true, Formatting.LIGHT_PURPLE, Formatting.DARK_PURPLE),
            generateUniqueMessage("Be Your Own Hero", "You have what it takes!", true, Formatting.RED, Formatting.YELLOW),
            generateUniqueMessage("You’re In Control", "Guide your thoughts with love.", true, Formatting.DARK_BLUE, Formatting.WHITE),
            generateUniqueMessage("Appreciate Your Journey", "It’s uniquely yours.", true, Formatting.DARK_GREEN, Formatting.GREEN),
            generateUniqueMessage("Keep Exploring", "Your curiosity leads to growth.", true, Formatting.AQUA, Formatting.BLUE),
            generateUniqueMessage("Stay Courageous", "Your bravery is inspiring.", true, Formatting.DARK_RED, Formatting.BLUE),
            generateUniqueMessage("Stay Hopeful", "The best is yet to come.", true, Formatting.BLUE, Formatting.YELLOW),
            generateUniqueMessage("Appreciate Your Growth", "Look at how far you've come!", true, Formatting.GREEN, Formatting.DARK_GREEN),
            generateUniqueMessage("Choose Joy Today", "It’s within reach.", true, Formatting.LIGHT_PURPLE, Formatting.GOLD),
            generateUniqueMessage("Inspire Change", "Your actions make a difference.", true, Formatting.DARK_PURPLE, Formatting.DARK_GREEN),
            generateUniqueMessage("Cherish This Moment", "You’ll never live it again.", true, Formatting.BLUE, Formatting.DARK_BLUE),
            generateUniqueMessage("Love Your Uniqueness", "You bring something special.", true, Formatting.WHITE, Formatting.AQUA),
            generateUniqueMessage("Stay Optimistic", "The world is full of possibilities.", true, Formatting.YELLOW, Formatting.BLUE),
            generateUniqueMessage("Seek Inspiration", "It’s all around you.", true, Formatting.GREEN, Formatting.GOLD),
            generateUniqueMessage("Stay Curious", "It’s the key to learning.", true, Formatting.DARK_PURPLE, Formatting.DARK_BLUE),
            generateUniqueMessage("Steve Jobs:", "The only way to do great work is to love what you do.", true, Formatting.GOLD, Formatting.DARK_BLUE),
            generateUniqueMessage("Theodore Roosevelt:", "Believe you can and you're halfway there.", true, Formatting.YELLOW, Formatting.DARK_GREEN),
            generateUniqueMessage("Mahatma Gandhi:", "You must be the change you wish to see in the world.", true, Formatting.GOLD, Formatting.GREEN),
            generateUniqueMessage("Sam Levenson:", "Don't watch the clock; do what it does. Keep going.", true, Formatting.GRAY, Formatting.DARK_BLUE),
            generateUniqueMessage("Robert Frost:", "The best way out is always through.", true, Formatting.DARK_RED, Formatting.DARK_GREEN),
            generateUniqueMessage("George Eliot:", "It is never too late to be what you might have been.", true, Formatting.DARK_GREEN, Formatting.GOLD),
            generateUniqueMessage("Pablo Picasso:", "Everything you can imagine is real.", true, Formatting.LIGHT_PURPLE, Formatting.DARK_PURPLE),
            generateUniqueMessage("Robin Williams:", "No matter what people tell you - words and ideas can change the world.", true, Formatting.DARK_RED, Formatting.RED),
            generateUniqueMessage("Lewis Carroll:", "In the end - we only regret the chances we didn't take.", true, Formatting.AQUA, Formatting.DARK_BLUE),
            generateUniqueMessage("Norman Vaughan:", "Dream big and dare to fail.", true, Formatting.LIGHT_PURPLE, Formatting.DARK_GRAY),
            generateUniqueMessage("Milton Berle:", "If opportunity doesn't knock - build a door.", true, Formatting.YELLOW, Formatting.GOLD),
            generateUniqueMessage("Walt Disney:", "All our dreams can come true - if we have the courage to pursue them.", true, Formatting.BLUE, Formatting.YELLOW),
            generateUniqueMessage("Nelson Mandela:", "It always seems impossible until it’s done.", true, Formatting.GRAY, Formatting.DARK_AQUA),
            generateUniqueMessage("Albert Einstein:", "A person who never made a mistake never tried anything new.", true, Formatting.DARK_PURPLE, Formatting.LIGHT_PURPLE),
            generateUniqueMessage("Mae Jemison:", "Never limit yourself because of others’ limited imagination.", true, Formatting.DARK_GREEN, Formatting.GOLD),
            generateUniqueMessage("Bo Bennett:", "Success is not in what you have - but who you are.", true, Formatting.DARK_GREEN, Formatting.GREEN)
    ));
}
