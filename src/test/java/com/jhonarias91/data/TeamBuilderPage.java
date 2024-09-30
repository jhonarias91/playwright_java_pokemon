package com.jhonarias91.data;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TeamBuilderPage extends BasePage{

    public static final String TB_BTN_NEW_TEAM_XPATH_SELECTOR = "//button[@value='team' and @name='newTop']";
    public static final String TB_INPUT_FORMAT_CSS_SELECTOR = "input[placeholder='Search formats']";
    public static final String TB_BTN_ROLE_BUTTON_NAME_CSS_SELECTOR = "format";
    //public static final String TB_BTN_TEAM_FORMAT_CSS_SELECTOR = "body > div.ps-popup > span > ul > details > li > button"; With this was resolving more than 200 elements
    //Value will be like this, gen7ubers but when the data change we need to get this dynamically
    public static final String TB_BTN_TEAM_FORMAT_CSS_SELECTOR = "button[value='%s'] ";//

    public static final String TB_BTN_ADD_POKEMON_CSS_SELECTOR = "button[name='addPokemon']";
    public static final String TB_BTN_VALIDATE_CSS_SELECTOR = "button[name='validate']";
    public static final String TB_POPUP_VALIDATE_CSS_SELECTOR = "body > div.ps-overlay > div > form > p:nth-child(1)";

    private final Locator btnNewTeam;
    private final Locator inputSelectFormat;
    private final Locator buttonSelectFormatOption;
    private final Locator btnTeamFormat;
    private final Locator btnAddPokemon;
    private final Locator btnValidate;
    private final Locator popUpValidate;

    private final Locator.PressSequentiallyOptions DEFAULT_SEQUENTIAL_DELAY
             = new Locator.PressSequentiallyOptions().setDelay(1);

    public TeamBuilderPage(Page page, String genFormatkey) {
        super(page);
        this.btnNewTeam = page.locator(TB_BTN_NEW_TEAM_XPATH_SELECTOR);
        this.inputSelectFormat = page.locator(TB_INPUT_FORMAT_CSS_SELECTOR);
        Page.GetByRoleOptions roleOptions = new Page.GetByRoleOptions();
        roleOptions.setName(TB_BTN_ROLE_BUTTON_NAME_CSS_SELECTOR);
        this.buttonSelectFormatOption = page.getByRole(AriaRole.BUTTON, roleOptions);
        this.btnTeamFormat = page.locator(String.format(TB_BTN_TEAM_FORMAT_CSS_SELECTOR,genFormatkey));
        this.btnAddPokemon = page.locator(TB_BTN_ADD_POKEMON_CSS_SELECTOR);
        this.btnValidate = page.locator(TB_BTN_VALIDATE_CSS_SELECTOR);
        this.popUpValidate = page.locator(TB_POPUP_VALIDATE_CSS_SELECTOR);
    }

    public void goToCreateTeam(){
        this.btnNewTeam.click();
    }
    public void enterGenAndFormat(String gen, String format){
        buttonSelectFormatOption.click();
        this.inputSelectFormat.pressSequentially("["+gen + "] " + format, DEFAULT_SEQUENTIAL_DELAY);
        this.btnTeamFormat.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        this.btnTeamFormat.click();
    }

    public void goToAddNewPokemon(){
        this.btnAddPokemon.click();
    }

    public void validateTeam(){
        this.btnValidate.click();
    }

    public String getPopUpText(){
        this.page.waitForSelector(TB_POPUP_VALIDATE_CSS_SELECTOR, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        return this.popUpValidate.innerText();
    }

}
