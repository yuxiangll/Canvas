package com.s0cl.inject.mixin.net.minecraft.resource;

import com.s0cl.managers.LanguageStateManager;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.search.SearchManager;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourceReloader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * @author yuxiangll
 * @since 2024/8/10 上午10:23
 * IntelliJ IDEA
 */

@Mixin(ReloadableResourceManagerImpl.class)
public abstract class MixinReloadableResourceManagerImpl {

    @ModifyArg(method = "reload", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/SimpleResourceReload;start(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Ljava/util/concurrent/CompletableFuture;Z)Lnet/minecraft/resource/ResourceReload;"))
    private List<ResourceReloader> onReload(List<ResourceReloader> reloaders) {
        return LanguageStateManager.INSTANCE.getResourceLoadViaLanguage() ? LanguageStateManager.INSTANCE.getReloaders() : reloaders;
    }

    @Inject(method = "registerReloader", at = @At("HEAD"))
    private void onRegisterReloader(ResourceReloader reloader, CallbackInfo ci) {
        if (reloader instanceof LanguageManager || reloader instanceof SearchManager) {
            LanguageStateManager.INSTANCE.addResourceReloader(reloader);
        }
    }



}
