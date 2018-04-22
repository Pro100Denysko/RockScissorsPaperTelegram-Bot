package ua.com.app;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class RockScissorsPaperBot extends TelegramLongPollingBot {

  private Keyboard keyboard = new Keyboard();

  private final static String PAPER = ":page_facing_up:";
  private final static String SCISSORS = ":scissors:";
  private final static String ROCK = ":new_moon:";
  private final static String PAPER_EMOJI = EmojiParser.parseToUnicode(PAPER);
  private final static String SCISSORS_EMOJI = EmojiParser.parseToUnicode(SCISSORS);
  private final static String ROCK_EMOJI = EmojiParser.parseToUnicode(ROCK);
//
  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      int RANDOM = (int) (Math.random() * 3 + 1);
      String result;
      if (RANDOM == 1) {
        result = ROCK_EMOJI;
      } else if (RANDOM == 2) {
        result = SCISSORS_EMOJI;
      } else {
        result = PAPER_EMOJI;
      }

      SendMessage message = new SendMessage()
          .setChatId(update.getMessage().getChatId())
          .enableMarkdown(true);
      message.setReplyMarkup(keyboard.getOneTwoThreeKeyboard());

      if (result(update.getMessage().getText(), RANDOM) == 1) {
        message.setText("Win" + "\nBot selected " + result);
      } else if (result(update.getMessage().getText(), RANDOM) == 2) {
        message.setText("Lose" + "\nBot selected " + result);
      } else if (result(update.getMessage().getText(), RANDOM) == 3) {
        message.setText("Draw" + "\nBot selected " + result);
      } else {
        message.setText("I don't know what did you mean");
      }
      try {
        execute(message);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  private int result(String userNumber, int botNumber) {
    //Win
    if (userNumber.equals(ROCK_EMOJI) && botNumber == 2
        || userNumber.equals(SCISSORS_EMOJI) && botNumber == 3
        || userNumber.equals(PAPER_EMOJI) && botNumber == 1) {
      return 1;
      //Lose
    } else if (userNumber.equals(ROCK_EMOJI) && botNumber == 3
        || userNumber.equals(SCISSORS_EMOJI) && botNumber == 1
        || userNumber.equals(PAPER_EMOJI) && botNumber == 2) {
      return 2;
      //Draw
    } else if (userNumber.equals(ROCK_EMOJI) && botNumber == 1
        || userNumber.equals(SCISSORS_EMOJI) && botNumber == 2
        || userNumber.equals(PAPER_EMOJI) && botNumber == 3) {
      return 3;
    } else {
      return 4;
    }

  }

  public String getBotUsername() {
    return "Rock Scissors Paper";
  }

  public String getBotToken() {
    return "591529694:AAGvFBtcT5G0CGID3JN8j-vDrcjjqMKkQCI";
  }
}