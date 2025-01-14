package theWeaponMaster.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theWeaponMaster.DefaultMod;
import theWeaponMaster.actions.ViciousAction;
import theWeaponMaster.characters.TheWeaponMaster;

import static theWeaponMaster.DefaultMod.makeCardPath;

public class BullyHuh extends AbstractBullyCard {

    public static final String ID = DefaultMod.makeID(BullyHuh.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheWeaponMaster.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;
    private static final int BULLY_COST = 10;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADED_MAGIC_NUMBER = 1;

    public BullyHuh() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = baseMagicNumber = MAGIC_NUMBER;
        this.exhaust = true;
        bullyNumber = baseBullyNumber = BULLY_COST;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return new ViciousAction().viciousUse(p, this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
        if (upgraded) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 10));
        }
        p.getPower("ViciousPower").amount -= bullyNumber;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADED_MAGIC_NUMBER);
            this.rawDescription = DESCRIPTION;
            initializeDescription();
        }
    }
}