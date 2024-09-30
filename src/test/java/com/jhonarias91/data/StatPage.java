package com.jhonarias91.data;

import com.jhonarias91.models.EVS;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class StatPage extends BasePage {

    public static final String STAT_INPUT_CSS_SELECTOR = "input[name='%s']";
    public static final String STAT_EM_CSS_SELECTOR = ".totalev em";

    public static final Locator.PressSequentiallyOptions PRESS_SEQUENTIALLY_DEFAULT_OPTIONS =
            new Locator.PressSequentiallyOptions().setDelay(1);

    private HashMap<String, Locator> evs;
    private Locator remainingEvs;

    /*
     * Constructor for MovePage
     * @inParam page: page to search the elements
     * @inParam statInputNames: the names key and their real values
     * */
    public StatPage(Page page, Map<String, String> statInputNames) {
        super(page);
        evs = new HashMap<>();
        for (String key : statInputNames.keySet()) {
            evs.put(key, page.locator(String.format(STAT_INPUT_CSS_SELECTOR, statInputNames.get(key))));
        }
        this.remainingEvs = page.locator(STAT_EM_CSS_SELECTOR);
    }

    /*
    * Send the evs to each field dynamically in case the evs can increase
    * */
    public void typeEvs(EVS evs) {
        Field[] fields = EVS.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            try {
                Method getterMethod = EVS.class.getMethod(getterName);
                Object evValue = getterMethod.invoke(evs);
                this.evs.get(fieldName).pressSequentially(evValue.toString(), PRESS_SEQUENTIALLY_DEFAULT_OPTIONS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getRemainingEvs(){
        return this.remainingEvs.innerText();
    }
}
