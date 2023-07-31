/*
 * Copyright (c) 2022, WSO2 LLC. (http://www.wso2.com). All Rights Reserved.
 *
 * This software is the property of WSO2 LLC. and its suppliers, if any.
 * Dissemination of any information or reproduction of any material contained
 * herein in any form is strictly forbidden, unless permitted by WSO2 expressly.
 * You may not alter or remove any copyright or other notice from copies of this content.
 */

package org.wso2.carbon.identity.api.user.organization.common.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.organization.management.service.OrganizationUserResidentResolverService;

/**
 * Factory Beans serves as a factory for creating other beans within the IOC container. This factory bean is used to
 * instantiate the OrganizationUserResidentResolverService type of object inside the container.
 */
public class OrganizationUserResidentResolverOSGIServiceFactory extends
        AbstractFactoryBean<OrganizationUserResidentResolverService> {

    private OrganizationUserResidentResolverService organizationUserResidentResolverService;

    @Override
    public Class<?> getObjectType() {

        return Object.class;
    }

    @Override
    protected OrganizationUserResidentResolverService createInstance() throws Exception {

        if (this.organizationUserResidentResolverService == null) {
            OrganizationUserResidentResolverService service =
                    (OrganizationUserResidentResolverService) PrivilegedCarbonContext.
                            getThreadLocalCarbonContext()
                            .getOSGiService(OrganizationUserResidentResolverService.class, null);
            if (service != null) {
                this.organizationUserResidentResolverService = service;
            } else {
                throw new Exception("Unable to retrieve OrganizationUserResidentResolverService service.");
            }
        }
        return this.organizationUserResidentResolverService;
    }
}
