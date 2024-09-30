package com.jhonarias91.data;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class MovePage extends BasePage {

    public static final String MOVE_INPUT_MOVE_CSS_SELECTOR = "input[name='%s']";
    public static final String MOVE_HEADER_STATS_XPATH_SELECTOR = "//button[@name='stats']/span[@class='statrow']/label[contains(text(), 'HP')]";
    public static final String MOVE_HEADER_ITEM_CSS_SELECTOR = "input[name='item']";
    public static final String MOVE_HEADER_ABILITY_CSS_SELECTOR = "input[name='ability']";
    public static final Locator.PressSequentiallyOptions PRESS_SEQUENTIALLY_DEFAULT_OPTIONS =
            new Locator.PressSequentiallyOptions().setDelay(1);

    private Locator item;
    private Locator ability;
    private List<Locator> moves;
    private Locator statsHeader;

    /*
     * Constructor for MovePage
     *@inParam page: page to search the elements
     * @inParam moveInputFieldsNames: the names for the movements text inputs
     * */
    public MovePage(Page page, List<String> moveInputFieldsNames) {
        super(page);
        moves = new ArrayList<>(moveInputFieldsNames.size());
        for (int i = 0; i < moveInputFieldsNames.size(); i++) {
            moves.add(page.locator(String.format(MOVE_INPUT_MOVE_CSS_SELECTOR, moveInputFieldsNames.get(i))));
        }
        this.statsHeader = page.locator(MOVE_HEADER_STATS_XPATH_SELECTOR);
        this.item = page.locator(MOVE_HEADER_ITEM_CSS_SELECTOR);
        this.ability = page.locator(MOVE_HEADER_ABILITY_CSS_SELECTOR);
    }

    public void enterItem(String itemName) {
        this.item.clear();
        this.item.pressSequentially(itemName, PRESS_SEQUENTIALLY_DEFAULT_OPTIONS);
    }

    public void enterAbility(String abilityName) {
        this.ability.clear();
        this.ability.pressSequentially(abilityName, PRESS_SEQUENTIALLY_DEFAULT_OPTIONS);
    }

    public void typeMoves(List<String> moves) {
        for (int i = 0; i < moves.size(); i++) {
            this.moves.get(i).fill(moves.get(i));
        }
    }

    public void goToStats() {
        this.statsHeader.click();
    }

}
