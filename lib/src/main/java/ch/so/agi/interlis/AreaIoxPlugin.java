package ch.so.agi.interlis;

import java.util.HashMap;

import ch.ehi.basics.settings.Settings;
import ch.interlis.ili2c.metamodel.TransferDescription;
import ch.interlis.ili2c.metamodel.Viewable;
import ch.interlis.iom.IomObject;
import ch.interlis.iox.IoxValidationConfig;
import ch.interlis.iox_j.logging.LogEventFactory;
import ch.interlis.iox_j.validator.InterlisFunction;
import ch.interlis.iox_j.validator.ObjectPool;
import ch.interlis.iox_j.validator.Validator;
import ch.interlis.iox_j.validator.Value;

public class AreaIoxPlugin implements InterlisFunction {
    public static final double strokeP = 0.002;

    private LogEventFactory logger = null;
    private TransferDescription td = null;
    private HashMap<String, Viewable> tag2class = null;
    private Validator validator = null;

    @Override
    public Value evaluate(String validationKind, String usageScope, IomObject mainObj, Value[] actualArguments) {        
        if (actualArguments[0].skipEvaluation()) {
            return actualArguments[0];
        }
        if (actualArguments[1].skipEvaluation()) {
            return actualArguments[1];
        }
        if (actualArguments[0].isUndefined()) {
            return Value.createSkipEvaluation();
        }
        if (actualArguments[1].isUndefined()) {
            return Value.createSkipEvaluation();
        }

        return Value.createSkipEvaluation();
    }

    @Override
    public String getQualifiedIliName() {
        return "SOGIS_Geometry.area";
    }

    @Override
    public void init(TransferDescription td, Settings settings, 
            IoxValidationConfig validationConfig, ObjectPool objectPool, 
            LogEventFactory logEventFactory) {

        this.logger = logEventFactory;
        this.logger.setValidationConfig(validationConfig);
        this.td = td;
        this.tag2class = ch.interlis.iom_j.itf.ModelUtilities.getTagMap(td);   
        this.validator = (Validator) settings.getTransientObject(this.IOX_VALIDATOR);
    }
}
