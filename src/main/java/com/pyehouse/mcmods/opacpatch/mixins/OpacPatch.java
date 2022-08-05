package com.pyehouse.mcmods.opacpatch.mixins;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xaero.pac.common.server.claims.protection.ChunkProtection;

@Pseudo
@Mixin(ChunkProtection.class)
public abstract class OpacPatch {

    @Shadow(remap = false)
    protected abstract boolean isIncludedByProtectedEntityLists(Entity e);

    @Inject(at = @At("TAIL"), method = "isProtectable(Lnet/minecraft/world/entity/Entity;)Z", cancellable = true, remap = false)
    private void mix_isProtectable(Entity e, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((e instanceof Player) || this.isIncludedByProtectedEntityLists(e));
    }
}
