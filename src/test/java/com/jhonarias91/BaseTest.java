package com.jhonarias91;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jhonarias91.models.PokemonTeam;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    private static final boolean IS_HEADLESS = true;
    private static final boolean ENABLE_TRACING = false;

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected PokemonTeam data;

    @BeforeAll
    public void beforeAll() throws IOException {
        this.playwright = Playwright.create();
        this.browser = playwright
                .chromium()
                .launch(new BrowserType
                        .LaunchOptions()
                        .setHeadless(IS_HEADLESS)
                        .setSlowMo(1));
        this.page = browser.newPage();
        if (ENABLE_TRACING){
            page.context().tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true) // get screenshots during tracing
                    .setSnapshots(true)   // Get dom and snaptshots
                    .setSources(true));   // Include source of the testing
        }
    }

    protected void readData(String dataPath) throws IOException {

        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(dataPath));
            this.data = gson.fromJson(reader, PokemonTeam.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public void tearDown(){
        if (ENABLE_TRACING){
            page.context().tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
        }
        browser.close();
        playwright.close();
    }

}
