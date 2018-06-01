package com.example.user1.storyapp.model;

import com.example.user1.storyapp.R;

/**
 * Created by ${joel} on ${8-12-2017}.
 */

public class Story {
    private Page[] pages;
    public Story(){
         pages = new Page[7];
         //page 0
        /* pages[0] = new Page();
         * pages[0].setImageId(R.drawable.page0);
         * pages[0].setTextId(R.string.page0);
         * pages[0].setChoice1(new Choice());
         * pages[0].setChoice2(new Choice());
         */
        //This is another method by using constractor in both page and story
        //page 0
        pages[0] = new Page( R.drawable.page0,
                R.string.page0,
                new Choice(R.string.page0_choice1, 1 ),
                new Choice(R.string.page0_choice2, 2));

         // page 1
        pages[1] = new Page(R.drawable.page1,
                R.string.page1,
                new Choice(R.string.page1_choice1, 3),
                new Choice(R.string.page1_choice2, 4));
        // page 2
        pages[2] = new Page(R.drawable.page2,
                R.string.page2,
                new Choice(R.string.page2_choice1, 4),
                new Choice(R.string.page2_choice2, 6));
        //page 3
        pages[3] = new Page(R.drawable.page3,
                R.string.page3,
                new Choice(R.string.page3_choice1, 4),
                new Choice(R.string.page3_choice2, 5));
        //page 4
        pages[4] = new Page(R.drawable.page4,
                R.string.page4,
                new Choice(R.string.page4_choice1, 5),
                new Choice(R.string.page4_choice2, 6));
        //page 5
        pages[5] = new Page(R.drawable.page5, R.string.page5);
        //page 6
        pages[6] = new Page(R.drawable.page6, R.string.page6);
    }
// getPage() is defined
    public Page getPage(int pageNumber) {
        //if the no of pageNumber is more than pages there is
        if (pageNumber>= pages.length){
            pageNumber = 0;
        }
        return pages[pageNumber];

    }
}
