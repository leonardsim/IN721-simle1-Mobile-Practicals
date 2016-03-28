package bit.simle1.languagetrainer;

import android.graphics.Bitmap;

/**
 * Created by Asus on 29/3/2016.
 */
public class Question {
    //Attributes
    private String article;
    private String noun;
    private Bitmap image;
    private String userAnswer;
    private boolean userCorrect;

    //Constructor
    public Question(String article, String noun, Bitmap image)
    {
        this.setArticle(article);
        this.setNoun(noun);
        this.setImage(image);
    }

    //Method
    // Checks to see if user's answer is correct
    public boolean checkIfUserCorrect()
    {
        if (userAnswer == article)
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
