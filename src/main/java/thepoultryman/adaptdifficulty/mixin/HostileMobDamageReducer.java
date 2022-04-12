package thepoultryman.adaptdifficulty.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thepoultryman.adaptdifficulty.AdaptiveDifficulty;

@Mixin(LivingEntity.class)
public class HostileMobDamageReducer {
	@Inject(at = @At("HEAD"), method = "applyDamage")
	private void adaptdifficulty$reducePlayerDamage(DamageSource source, float amount, CallbackInfo ci) {
		if (source.getAttacker() instanceof ServerPlayerEntity player) {
			amount = (float) (amount - (Math.pow(1.1, (player.experienceLevel - 30)) + 1));
			AdaptiveDifficulty.LOGGER.info(String.valueOf(amount));
		}
	}
}
