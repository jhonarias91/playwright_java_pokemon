package com.jhonarias91.data;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PokemonSearchPage extends BasePage{

    public static final String SEARCH_INPUT_POKEMON_NAME_CSS_SELECTOR = "input[name='pokemon']";
    public static final String SEARCH_BUTTON_GO_BACK_CSS_SELECTOR = "button[name='back']";

    private Locator pokemonNameText;
    private Locator goToTeamButton;

    public PokemonSearchPage(Page page){
        super(page);
        this.pokemonNameText = this.page.locator(SEARCH_INPUT_POKEMON_NAME_CSS_SELECTOR);
        this.goToTeamButton = this.page.locator(SEARCH_BUTTON_GO_BACK_CSS_SELECTOR);
    }

    public void searchPokemonByName(String pokemonName){
        this.pokemonNameText.pressSequentially(pokemonName, new Locator.PressSequentiallyOptions().setDelay(50));
        this.pokemonNameText.press("Enter");
    }

    public void goToteam(){
        this.goToTeamButton.click();
    }

}
