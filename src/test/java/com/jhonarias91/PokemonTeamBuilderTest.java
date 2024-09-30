package com.jhonarias91;

import com.jhonarias91.data.*;
import com.jhonarias91.models.Pokemon;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PokemonTeamBuilderTest extends BaseTest {

    public static final String TEST_DATA_POKEMON_DATA_DRIVEN_JSON_PATH =
            "src/test/resources/pokemon_data_driven.json";

    private MainPage mainPage;
    private TeamBuilderPage teamBuilderPage;
    private MovePage movePage;
    private StatPage statPage;
    private PokemonSearchPage pokemonSearchPage;

    @BeforeAll
    public void beforeAll() throws IOException {
        super.beforeAll();
        super.readData(TEST_DATA_POKEMON_DATA_DRIVEN_JSON_PATH);
        this.mainPage = new MainPage(super.page);
        String genFormatkey = data.getGen().trim() + super.data.getFormat();
        genFormatkey = genFormatkey.toLowerCase().replace(" ", "");
        this.teamBuilderPage = new TeamBuilderPage(super.page, genFormatkey);
        this.movePage = new MovePage(page, data.getMoveInputNames());
        this.statPage = new StatPage(page, data.getStatInputNames());
        this.pokemonSearchPage = new PokemonSearchPage(page);
    }

    @Test
    public void testCreateTeamOk() {

        mainPage.navigateTo("https://play.pokemonshowdown.com");
        mainPage.goToTeamBuilder();

        //Star creating a team
        teamBuilderPage.goToCreateTeam();
        //Choose the format and generation
        teamBuilderPage.enterGenAndFormat(data.getGen(), data.getFormat());

        for (Pokemon currentPokemon : data.getTeam()) {
            teamBuilderPage.goToAddNewPokemon();
            //Search for the pokemon
            pokemonSearchPage.searchPokemonByName(currentPokemon.getName());

            //Enter the moves
            movePage.enterItem(currentPokemon.getItem());
            movePage.enterAbility(currentPokemon.getAbility());
            movePage.typeMoves(currentPokemon.getMoves());

            //Enter stats
            movePage.goToStats();

            statPage.typeEvs(currentPokemon.getEvs());
            String remainingEvs = statPage.getRemainingEvs();

            Assertions.assertEquals("0", remainingEvs);
            pokemonSearchPage.goToteam();
        }

        teamBuilderPage.validateTeam();
        Assertions.assertEquals(String.format("Your team is valid for [%s] %s.",
                data.getGen(), data.getFormat()), teamBuilderPage.getPopUpText());

    }

}