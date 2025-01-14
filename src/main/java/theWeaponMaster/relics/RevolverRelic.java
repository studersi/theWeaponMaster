package theWeaponMaster.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWeaponMaster.DefaultMod;
import theWeaponMaster.powers.ReloadPower;
import theWeaponMaster.util.TextureLoader;

import static theWeaponMaster.DefaultMod.makeRelicOutlinePath;
import static theWeaponMaster.DefaultMod.makeRelicPath;
import static theWeaponMaster.patches.WeaponMasterTags.REVOLVER;

public class RevolverRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID(RevolverRelic.class.getSimpleName());
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    public static int SHOTS = 6;
    public static AbstractCreature owner;

    public RevolverRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
        this.counter = SHOTS;

        owner = owner;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(REVOLVER)) {
            this.counter--;
            if (this.counter <= 0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ReloadPower()));
            }
        }
    }

    public void onEquip() {
    }

    @Override
    public void atBattleStart() {
        if (this.counter <= 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ReloadPower()));
        }
    }

    @Override
    public void onEnterRestRoom() {
        super.onEnterRestRoom();
    }
}
