package com.s0cl.inject.mixin.net.minecraft.client.network;



import com.s0cl.Canvas;
import com.s0cl.events.EventAttackBlock;
import com.s0cl.events.EventBreakBlock;
import com.s0cl.module.imp.player.NoBreakCooldown;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author yuxiangll
 * @since 2024/8/9 下午8:04
 * IntelliJ IDEA
 */


@Mixin(ClientPlayerInteractionManager.class)
abstract public class MixinClientPlayerInteractionManager{

    @Unique
    private final MinecraftClient mc = MinecraftClient.getInstance();

    @ModifyConstant(method = "updateBlockBreakingProgress",constant = @Constant(intValue = 5))
    private int MiningCooldownFix(int value){
        if (NoBreakCooldown.INSTANCE.getEnable())
            return 0;
        else
            return value;
    }




    @Inject(method = "attackBlock", at = @At("HEAD"), cancellable = true)
    private void attackBlockHook(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if(mc.player == null || mc.world == null) return;

        EventAttackBlock event = new EventAttackBlock(pos, direction);
        Canvas.INSTANCE.getEventBus().post(event);
        if (event.isCancelled())
            cir.setReturnValue(false);
    }



    @Inject(method = "breakBlock", at = @At("HEAD"), cancellable = true)
    public void breakBlockHook(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(mc.player == null || mc.world == null) return;
        EventBreakBlock event = new EventBreakBlock(pos);
        Canvas.INSTANCE.getEventBus().post(event);
        if (event.isCancelled())
            cir.setReturnValue(false);
    }


}