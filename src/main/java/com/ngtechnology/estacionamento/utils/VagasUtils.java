package com.ngtechnology.estacionamento.utils;




import com.ngtechnology.estacionamento.handler.exception.PartnerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;




public class VagasUtils {
    private static final Logger logger = LoggerFactory.getLogger(VagasUtils.class);

    public static void validatedHeader(String partner) throws PartnerException {
        validatedPartner(partner);

    }

    public static Boolean validatedPartner(String partner) throws PartnerException {
        if (!ObjectUtils.isEmpty(partner))
            if (partner.equals("Star-Park") || partner.equals("Center-Park") || partner.equals("Downton-Park")) {
                return true;

            }
        else{
                    logger.warn("m=validatePartner - status=warn ");
                    throw new PartnerException(String.format
                            ("O partner informado é inválido."));
        }

        return null;
    }
}


