/*
 * All Rights Reserved (c) MoriyaShiine
 */

package moriyashiine.bewitchment.client.integration.emi.recipe;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import moriyashiine.bewitchment.client.integration.emi.BWEmiIntegration;
import moriyashiine.bewitchment.common.recipe.IncenseRecipe;
import moriyashiine.bewitchment.common.registry.BWObjects;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;

public class EmiIncenseRecipe extends BasicEmiRecipe {
	public EmiIncenseRecipe(IncenseRecipe recipe) {
		super(BWEmiIntegration.INCENSE_CATEGORY, recipe.getId(), 0, 18);
		for (Ingredient ingredient : recipe.input) {
			if (!ingredient.isEmpty()) {
				inputs.add(EmiIngredient.of(ingredient));
				width += 18;
			}
		}
		width += 58;
		outputs.add(EmiStack.of(BWObjects.BRAZIER.asItem().getDefaultStack().setCustomName(Text.translatable(recipe.effect.getTranslationKey()))));
	}

	@Override
	public void addWidgets(WidgetHolder widgets) {
		int x = 0;
		for (EmiIngredient emiIngredient : inputs) {
			widgets.addSlot(emiIngredient, x, 0);
			x += 18;
		}
		widgets.addTexture(EmiTexture.EMPTY_ARROW, x + 8, 1);
		widgets.addSlot(outputs.get(0), x + 40, 0).recipeContext(this);
	}
}
