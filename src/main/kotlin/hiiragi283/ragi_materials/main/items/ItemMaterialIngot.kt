package hiiragi283.ragi_materials.main.items

import hiiragi283.ragi_lib.main.base.ItemBase
import hiiragi283.ragi_materials.main.Reference
import hiiragi283.ragi_materials.main.materials.EnumMaterials
import hiiragi283.ragi_materials.main.materials.MaterialHelper
import hiiragi283.ragi_materials.main.materials.MaterialTypes
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterialIngot : ItemBase(Reference.MOD_ID, "ingot", 255) {

    override fun getItemStackDisplayName(stack: ItemStack): String {
        //EnumMaterialの取得
        val material = MaterialHelper.getMaterial(stack.metadata)
        return I18n.format("item.ragi_ingot.name", I18n.format("material.${material.registryName}"))
    }

    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0 until 255) {
                //EnumMaterialsを取得
                val material = MaterialHelper.getMaterial(i)
                //materialがWILDCARDでない場合
                if (material != EnumMaterials.WILDCARD) {
                    //materialのtypeがMETALの場合
                    if (material.type == MaterialTypes.METAL) {
                        //ItemStackをlistに追加
                        subItems.add(ItemStack(this, 1, i))
                    }
                }
            }
        }
    }
}