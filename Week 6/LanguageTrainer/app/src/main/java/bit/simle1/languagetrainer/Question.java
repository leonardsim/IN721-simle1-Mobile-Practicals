package bit.simle1.languagetrainer;

import android.graphics.drawable.Drawable;

/**
 * Created by Asus on 29/3/2016.
 */
public class Question {
    //Attributes
    private String article;
    private String noun;
    private Drawable image;
    private String userAnswer;
    private boolean userCorrect;

    //Constructor
    public Question(String article, String noun, Drawable image)
    {
        this.setArticle(article);
        this.setNoun(noun);
        this.setImage(image);
    }

    //Method
    // Checks to see if user's answer is correct
    public boolean checkIfUserCorrect()
    {
        if (getUserAnswer() == article)
        {
            userCorrect = true;
        }
        else
        {
            userCorrect = false;
        }

        return userCorrect;
    }

    //Accessors
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
