package com.example.RecipesByElina.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.RecipesByElina.domain.Ingredient;
import com.example.RecipesByElina.domain.IngredientRepository;
import com.example.RecipesByElina.domain.InstructionRepository;
import com.example.RecipesByElina.domain.Instructions;
import com.example.RecipesByElina.domain.Recipe;
import com.example.RecipesByElina.domain.RecipeRepository;
import com.example.RecipesByElina.domain.Unit;
import com.example.RecipesByElina.domain.UnitRepository;

@Controller
public class RecipeController {

	// Repositories and their getters and setters
	@Autowired
	private RecipeRepository recipeRep;

	@Autowired
	private IngredientRepository ingredientRep;

	@Autowired
	private UnitRepository unitRep;

	@Autowired
	private InstructionRepository instructionRep;

	public RecipeRepository getRecipeRepository() {
		return recipeRep;
	}

	public void setRecipeRepository(RecipeRepository recipeRep) {
		this.recipeRep = recipeRep;
	}

	public IngredientRepository getIngredientRepository() {
		return ingredientRep;
	}

	public void setIngredientRepository(IngredientRepository ingredientRep) {
		this.ingredientRep = ingredientRep;
	}

	public UnitRepository getUnitRepository() {
		return unitRep;
	}

	public void setUnitRepository(UnitRepository unitRep) {
		this.unitRep = unitRep;
	}

	public InstructionRepository getInstructionRepository() {
		return instructionRep;
	}

	public void setInstructionRepository(InstructionRepository instructionRep) {
		this.instructionRep = instructionRep;
	}

	//
	//
	//
	//
	// processes regarding recipe pages and the recipe table

	// mapper for recipe list
	@RequestMapping(value = { "", "/", "/recipes" })
	public String recipeList(Model model) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
    	model.addAttribute("name", username);
		List<Recipe> recipes = recipeRep.findAll();
		model.addAttribute("recipes", recipes);
		return "recipes";
	}

	// mapper for showcasing the chosen recipe
	@RequestMapping(value = "/recipe/{id}", method = RequestMethod.GET)
	public String viewRecipe(@PathVariable("id") int recipeId, Model model) {
		model.addAttribute("recipe", recipeRep.findById(recipeId));
		model.addAttribute("ingredients", ingredientRep.findByRecipeId(recipeId));
		model.addAttribute("instructions", instructionRep.findByRecipeId(recipeId));
		model.addAttribute("units", unitRep.findAll());
		return "recipe";
	}

	// edit recipe (as in the whole thing, like ingredients, instructions, etc.)
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editrecipe/{id}", method = RequestMethod.GET)
	public String editRecipe(@PathVariable("id") int recipeId, Model model) {
		model.addAttribute("recipe", recipeRep.findById(recipeId));
		model.addAttribute("ingredients", ingredientRep.findByRecipeId(recipeId));
		model.addAttribute("instructions", instructionRep.findByRecipeId(recipeId));
		model.addAttribute("units", unitRep.findAll());

		Ingredient newIngredient = new Ingredient();
		newIngredient.setRecipeId(recipeId);

		Instructions newInstruction = new Instructions();
		newInstruction.setRecipeId(recipeId);
		newInstruction.setStepNum(instructionRep.getNewStepNum(recipeId));
		
		Recipe updatedRecipe = recipeRep.findById(recipeId);
		
		model.addAttribute("updatedRecipe", updatedRecipe);
		model.addAttribute("ingredient", newIngredient);
		model.addAttribute("instruction", newInstruction);
		return "editrecipe";
	}
	
	// update recipe info (description, time, name)
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/updaterecipe", method = RequestMethod.POST)
	public String updateRecipe(Recipe recipe, Model model) {
		recipeRep.updateRecipe(recipe);
		return "redirect:/editrecipe/" + recipe.getId();
	}

	// mapper for adding recipes
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addrecipe")
	public String addRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		return "addrecipe";
	}

	// save the added recipe
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveaddedrecipe", method = RequestMethod.POST)
	public String saveAddedRecipe(Recipe recipe) {
		int recipeId = recipeRep.addRecipe(recipe);
		return "redirect:/editrecipe/" + recipeId;
	}

	// delete recipe
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleterecipe/{id}", method = RequestMethod.GET)
	public String deleteRecipe(@PathVariable("id") int recipeId, Model model) {
		recipeRep.deleteRecipeById(recipeId);
		return "redirect:../recipes";
	}

	//
	//
	//
	//
	// processes regarding ingredients

	// save added ingredient
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveingredient", method = RequestMethod.POST)
	public String saveIngredient(Ingredient ingredient, Model model) {
		ingredientRep.addIngredient(ingredient);
		return "redirect:/editrecipe/" + ingredient.getRecipeId();
	}

	// delete ingredient
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleteingredient/{ingredientid}", method = RequestMethod.GET)
	public String deleteIngredient(@PathVariable("ingredientid") int ingredientId, Model model) {
		int recipeId = ingredientRep.findRecipeIdByIngredientId(ingredientId);
		ingredientRep.deleteIngredientById(ingredientId);
		return "redirect:/editrecipe/" + recipeId;
	}
	
	// mapper for the edit instruction -page 
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editingredient/{id}", method = RequestMethod.GET)
	public String editIngredient(@PathVariable("id") int id, Model model) {
		Ingredient ingredient = ingredientRep.findById(id);
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("units", unitRep.findAll());
		return "editingredient";
	}
	
	// update instruction
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/updateingredient", method = RequestMethod.POST)
	public String updateIngredient(Ingredient ingredient, Model model) {
		ingredientRep.updateIngredient(ingredient);
		
		return "redirect:/editrecipe/" + ingredient.getRecipeId();
	}

	//
	//
	//
	//
	// processes regarding instructions

	// save added instruction
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveinstruction", method = RequestMethod.POST)
	public String saveInstruction(Instructions instruction) {
		instructionRep.addInstruction(instruction);
		return "redirect:/editrecipe/" + instruction.getRecipeId();
	}

	// delete instruction
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleteinstruction/{instructionid}", method = RequestMethod.GET)
	public String deleteInstruction(@PathVariable("instructionid") int instructionId, Model model) {
		Instructions instruction = instructionRep.findById(instructionId);
		int recipeId = instruction.getRecipeId();
		instructionRep.deleteInstruction(instruction);
		return "redirect:/editrecipe/" + recipeId;
	}
	
	// mapper for the edit instruction -page 
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editinstruction/{id}", method = RequestMethod.GET)
	public String editInstruction(@PathVariable("id") int id, Model model) {

		Instructions instruction = instructionRep.findById(id);
		model.addAttribute("instruction", instruction);

		return "editinstruction";
	}
	
	// update instruction
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/updateinstruction", method = RequestMethod.POST)
	public String updateInstruction(Instructions instruction, Model model) {
		instructionRep.updateInstruction(instruction);
		return "redirect:/editrecipe/" + instruction.getRecipeId();
	}

	//
	//
	//
	//
	// processes regarding units

	// mapper for the units page
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/units")
	public String unitsList(Model model) {
		List<Unit> units = unitRep.findAll();
		model.addAttribute("units", units);
		model.addAttribute("unit", new Unit());
		return "units";
	}

	// save an added unit
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/saveunit", method = RequestMethod.POST)
	public String saveAddedUnit(Unit unit) {
		unitRep.addUnit(unit);
		return "redirect:/units";
	}

	// mapper for the page where you can edit/update already existing units
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editunit/{id}", method = RequestMethod.GET)
	public String editUnit(@PathVariable("id") int id, Model model) {

		/*
		 * List<Unit> units = unitRep.findAll(); model.addAttribute("units", units);
		 * model.addAttribute("addUnit", new Unit());
		 */

		Unit unitToBeUpdated = unitRep.findById(id);
		model.addAttribute(unitToBeUpdated);

		Unit updatedUnit = new Unit();
		updatedUnit.setUnitId(id);
		model.addAttribute("updatedUnit", updatedUnit);

		return "editunit";
	}

	// update unit
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/updateunit", method = RequestMethod.POST)
	public String updateUnit(Unit unit, Model model) {
		unitRep.updateUnit(unit);
		return "redirect:/units";
	}

	// delete unit
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deleteunit/{id}", method = RequestMethod.GET)
	public String deleteUnit(@PathVariable("id") int id, Model model) {
		unitRep.deleteUnit(id);
		return "redirect:../units";
	}
    
    //
    //
    //
    //
    // others
    
    @RequestMapping(value="/login")
    public String login() {
    	return "login";
    }
	
}
