package ua.com.app;

import com.vdurmont.emoji.EmojiParser;
import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class Keyboard {

  public ReplyKeyboardMarkup getOneTwoThreeKeyboard() {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);

    List<KeyboardRow> keyboard = new ArrayList<>();
    KeyboardRow keyboardRow = new KeyboardRow();

    String paper = ":page_facing_up:";
    String scissors = ":scissors:";
    String rock = ":new_moon:";
    String paperEmoji = EmojiParser.parseToUnicode(paper);
    String scissorsEmoji = EmojiParser.parseToUnicode(scissors);
    String rockEmoji = EmojiParser.parseToUnicode(rock);

    keyboardRow.add(rockEmoji);
    keyboardRow.add(scissorsEmoji);
    keyboardRow.add(paperEmoji);
    keyboard.add(keyboardRow);
    replyKeyboardMarkup.setKeyboard(keyboard);
    return replyKeyboardMarkup;
  }

}
