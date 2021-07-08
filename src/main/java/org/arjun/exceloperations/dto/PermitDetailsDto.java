package org.arjun.exceloperations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermitDetailsDto {
    private static final long serialVersionUID = 1L;

    @JsonProperty("permit_number")
    private String permitNumber;
    @JsonProperty("permit_type")
    private Integer permitType;
    @JsonProperty("permit_type_definition")
    private String permitTypeDefinition;
    @JsonProperty("permit_creation_date")
    private Date permitCreationDate;
    @JsonProperty("block")
    private String block;
    @JsonProperty("lot")
    private String lot;
    @JsonProperty("street_number")
    private Integer streetNumber;
    @JsonProperty("street_name")
    private String streetName;
    @JsonProperty("street_suffix")
    private String streetSuffix;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("status_date")
    private Date statusDate;
    @JsonProperty("filed_date")
    private Date filedDate;
    @JsonProperty("issued_date")
    private Date issuedDate;
    @JsonProperty("number_of_existing_stories")
    private Integer numberOfExistingStories;
    @JsonProperty("number_of_proposed_stories")
    private Integer numberOfProposedStories;
    @JsonProperty("permit_expiration_date")
    private Date permitExpirationDate;
    @JsonProperty("estimated_cost")
    private Double estimatedCost;
    @JsonProperty("revised_cost")
    private Double revisedCost;
    @JsonProperty("existing_use")
    private String existingUse;
    @JsonProperty("existing_units")
    private Integer existingUnits;
    @JsonProperty("proposed_use")
    private String proposedUse;
    @JsonProperty("proposed_units")
    private Integer proposedUnits;
    @JsonProperty("plansets")
    private Integer plansets;
    @JsonProperty("existing_construction_type")
    private Integer existingConstructionType;
    @JsonProperty("existing_construction_type_description")
    private String existingConstructionTypeDescription;
    @JsonProperty("proposed_construction_type")
    private Integer proposedConstructionType;
    @JsonProperty("proposed_construction_type_description")
    private String proposedConstructionTypeDescription;
    @JsonProperty("supervisor_district")
    private Integer supervisorDistrict;
    @JsonProperty("neighborhoods_analysis_boundaries")
    private String neighborhoodsAnalysisBoundaries;
    @JsonProperty("zipcode")
    private String zipcode;
    @JsonProperty("location")
    private LocationDto location;
    @JsonProperty("record_id")
    private String recordId;
}
