package com.s0cl.inject.mixin.net.minecraft.entity.player;


import com.s0cl.Canvas;
import com.s0cl.events.EventPlayerJump;
import com.s0cl.events.EventPlayerTravel;
import com.s0cl.events.EventPostAttack;
import com.s0cl.module.imp.combat.Reach;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author yuxiangll
 * @since 2024/8/9 下午8:11
 * IntelliJ IDEA
 */
@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity{

    @Unique
    private final MinecraftClient mc = MinecraftClient.getInstance();

    protected MixinPlayerEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = {"travel"}, at = {@At("HEAD")}, cancellable = true)
    private void travel(Vec3d movement, CallbackInfo info) {
        EventPlayerTravel eventPlayerTravel = new EventPlayerTravel(movement.getX(), movement.getY(), movement.getZ());
        Canvas.INSTANCE.getEventBus().post(eventPlayerTravel);
        if (eventPlayerTravel.isCancelled()) info.cancel();
    }

//    @Inject(method = {"jump"}, at = {@At("HEAD")})
//    private void jump(CallbackInfo callback) {
//        if (mc.player == null) return;
//        Canvas.INSTANCE.getEventBus().post(new EventPlayerJump());
//    }



    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void attackAHook2(Entity target, CallbackInfo ci) {
        final EventPostAttack event = new EventPostAttack(target);
        Canvas.INSTANCE.getEventBus().post(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method = "getBlockInteractionRange", at = @At("HEAD"), cancellable = true)
    public void getBlockInteractionRangeHook(CallbackInfoReturnable<Double> cir) {
        if (Reach.INSTANCE.getEnable()){
            cir.setReturnValue((Double) Reach.INSTANCE.getBlockRange().getValue());
        }
    }

    @Inject(method = "getEntityInteractionRange", at = @At("HEAD"), cancellable = true)
    public void getEntityInteractionRangeHook(CallbackInfoReturnable<Double> cir) {
        if (Reach.INSTANCE.getEnable()) {
            cir.setReturnValue((double) Reach.INSTANCE.getAttackRange().getValue());
        }
    }
}

