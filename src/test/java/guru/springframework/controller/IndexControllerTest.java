package guru.springframework.controller;

import guru.springframework.controllers.IndexController;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {


    @Mock
    private RecipeService recipeService;

    private IndexController indexController;

    private MockMvc mockMvc;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);

        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void testMockMvc() throws Exception {

        mockMvc.perform(get("/indx"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"));

    }

    @Test
    public void getIndexPageTest() {

        Set<Recipe> recipeSet = new HashSet<>();

        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);

        recipeSet.add(recipe1);
        recipeSet.add(recipe2);

        Model model = Mockito.mock(Model.class);
        when(recipeService.getRecipes()).thenReturn(recipeSet);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String indexString = indexController.getIndexPage(model);

        Assert.assertEquals("index", indexString);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> argCapt = argumentCaptor.getValue();
        Assert.assertEquals(2, argCapt.size());


    }

}
