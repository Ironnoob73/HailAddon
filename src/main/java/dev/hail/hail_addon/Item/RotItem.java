package dev.hail.hail_addon.Item;

import com.mojang.logging.LogUtils;
import com.yuushya.block.blockstate.YuushyaBlockStates;
import com.yuushya.item.AbstractToolItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.slf4j.Logger;

public class RotItem extends AbstractToolItem {
    private static final Logger LOGGER = LogUtils.getLogger();
    public RotItem(Properties properties, Integer tipLines) {
        super(properties, tipLines);
    }
    public InteractionResult inMainHandRightClickOnBlock(Player player, BlockState blockState, Level level, BlockPos blockPos, ItemStack handItemStack) {
        Property<?> form = getRotateFromState(blockState);
        return form != null ? transformOneProperty(player, blockState, level, blockPos, handItemStack, form, false) : InteractionResult.PASS;
    }

    public InteractionResult inMainHandLeftClickOnBlock(Player player, BlockState blockState, Level level, BlockPos blockPos, ItemStack handItemStack) {
        Property<?> form = getRotateFromState(blockState);
        return form != null ? transformOneProperty(player, blockState, level, blockPos, handItemStack, form, true) : InteractionResult.PASS;
    }

    private static <T extends Comparable<T>> InteractionResult transformOneProperty(Player player, BlockState blockState, Level level, BlockPos blockPos, ItemStack handItemStack, Property<T> property, boolean doGetPre) {
        if (blockState.hasProperty(property)) {
            BlockState blockState2 = YuushyaBlockStates.cycleState(blockState, property, doGetPre);
            level.setBlock(blockPos, blockState2, 18);
            player.displayClientMessage(Component.translatable(handItemStack.getDescriptionId() + ".select", new Object[]{property.getName(), property.getName(blockState2.getValue(property))}), true);
            return InteractionResult.CONSUME;
        } else {
            return InteractionResult.PASS;
        }
    }public static EnumProperty getRotateFromState(BlockState blockState) {
        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            return BlockStateProperties.HORIZONTAL_FACING;
        }
        else if(blockState.hasProperty(BlockStateProperties.FACING)){
            return BlockStateProperties.FACING;
        }
        else if(blockState.hasProperty(BlockStateProperties.AXIS)){
            return BlockStateProperties.AXIS;
        }
        else{
            return null;
        }
    }
}
