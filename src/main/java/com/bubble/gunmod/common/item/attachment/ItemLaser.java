package com.bubble.gunmod.common.item.attachment;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemLaser extends ItemAttachment{

	public ItemLaser(String name) {
		super(name);
	}

	@Override
	public void runEffect(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runUseEffect(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runClientSideEffect(World world, EntityPlayer player) 
	{
		RayTraceResult mop = getMouseOver(0);// Minecraft.getMinecraft().objectMouseOver;//rayTrace(p, 35, 1.0F);
		
		if (mop == null) return;
		if (mop.typeOfHit == RayTraceResult.Type.BLOCK || mop.typeOfHit == RayTraceResult.Type.ENTITY) {
			double x = mop.hitVec.x;
			double y = mop.hitVec.y;
			double z = mop.hitVec.z;
			world.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, 0, 0, 0, 0);
		}		
	}
	
//	public void RayTraceResult rayTrace(Entity player, double distance, float partialTick)
//	{
//		return player.rayTrace(10, partialTick);
//	}
	
	public RayTraceResult getMouseOver(float partialTicks) {
		Minecraft mc = Minecraft.getMinecraft();
		Entity entity = mc.getRenderViewEntity();
		RayTraceResult raytraceresult = null;

		if (entity != null) {
			if (mc.world != null) {
				mc.mcProfiler.startSection("pick");
				mc.pointedEntity = null;
				double d0 = 10;
				raytraceresult = entity.rayTrace(d0, partialTicks);
				Vec3d vec3d = entity.getPositionEyes(partialTicks);
				double d1 = d0;

				if (raytraceresult != null) {
					d1 = raytraceresult.hitVec.distanceTo(vec3d);
				}

				Vec3d vec3d1 = entity.getLook(partialTicks);
				Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
				Entity pointedEntity = null;
				Vec3d vec3d3 = null;
				List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox()
						.offset(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).expand(1.0D, 1.0D, 1.0D),
						Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>() {
							public boolean apply(@Nullable Entity p_apply_1_) {
								return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
							}
						}));
				double d2 = d1;

				for (int j = 0; j < list.size(); ++j) {
					Entity entity1 = (Entity) list.get(j);
					AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox()
							.grow((double) entity1.getCollisionBorderSize());
					RayTraceResult raytraceresult2 = axisalignedbb.calculateIntercept(vec3d, vec3d2);

					if (axisalignedbb.contains(vec3d)) {
						if (d2 >= 0.0D) {
							pointedEntity = entity1;
							vec3d3 = raytraceresult2 == null ? vec3d : raytraceresult2.hitVec;
							d2 = 0.0D;
						}
					} else if (raytraceresult2 != null) {
						double d3 = vec3d.distanceTo(raytraceresult2.hitVec);

						if (d3 < d2 || d2 == 0.0D) {
							if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity()
									&& !entity.canRiderInteract()) {
								if (d2 == 0.0D) {
									pointedEntity = entity1;
									vec3d3 = raytraceresult2.hitVec;
								}
							} else {
								pointedEntity = entity1;
								vec3d3 = raytraceresult2.hitVec;
								d2 = d3;
							}
						}
					}
				}

				if (pointedEntity != null && (d2 < d1 || raytraceresult == null)) {
					raytraceresult = new RayTraceResult(pointedEntity, vec3d3);

					if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame) {
						mc.pointedEntity = pointedEntity;
					}
				}
			}
		}
		return raytraceresult;
	}

	@Override
	public void runClientSideUseEffect(World world, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttachmentType getType()
	{
		return AttachmentType.LASER;
	}

}
