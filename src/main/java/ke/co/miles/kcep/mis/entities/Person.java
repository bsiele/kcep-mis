/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author siech
 */
@Entity
@Table(name = "person", catalog = "kcep_mis", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findBySexId", query = "SELECT p FROM Person p WHERE p.sex.id = :sexId"),
    @NamedQuery(name = "Person.findByWardId", query = "SELECT p FROM Person p WHERE p.location.ward.id = :wardId"),
    @NamedQuery(name = "Person.findByCountyId", query = "SELECT p FROM Person p WHERE p.location.county.id = :countyId"),
    @NamedQuery(name = "Person.findBySubCountyId", query = "SELECT p FROM Person p WHERE p.location.subCounty.id = :subCountyId"),
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByNationalId", query = "SELECT p FROM Person p WHERE p.nationalId = :nationalId"),
    @NamedQuery(name = "Person.findByDateOfBirth", query = "SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Person.findByBusinessName", query = "SELECT p FROM Person p WHERE p.businessName = :businessName")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "national_id")
    private String nationalId;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 45)
    @Column(name = "business_name")
    private String businessName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Trainee> traineeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer")
    private List<Feedback> feedbackList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Trainer> trainerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<SoilFertilityPackage> soilFertilityPackageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<ValidationWorkshops> validationWorkshopsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wardExtensionOfficer")
    private List<ExtensionAndFieldVisitData> extensionAndFieldVisitDataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<Technology> technologyList;
    @OneToMany(mappedBy = "person")
    private List<EVoucher> eVoucherList;
    @OneToOne(mappedBy = "warehouseOperator")
    private Warehouse warehouse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<OnFarmTrialsAndDemonstrations> onFarmTrialsAndDemonstrationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<ExtensionMaterialAndGuideline> extensionMaterialAndGuidelineList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<DisseminationOfResults> disseminationOfResultsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wardExtensionOfficer")
    private List<SampledFarmerData> sampledFarmerDataList;
    @JoinColumn(name = "contact", referencedColumnName = "id")
    @ManyToOne
    private Contact contact;
    @JoinColumn(name = "designation_in_group", referencedColumnName = "id")
    @ManyToOne
    private DesignationInGroup designationInGroup;
    @JoinColumn(name = "farmer_group", referencedColumnName = "id")
    @ManyToOne
    private FarmerGroup farmerGroup;
    @JoinColumn(name = "farmer_sub_group", referencedColumnName = "id")
    @ManyToOne
    private FarmerSubGroup farmerSubGroup;
    @JoinColumn(name = "location", referencedColumnName = "id")
    @ManyToOne
    private Location location;
    @JoinColumn(name = "sex", referencedColumnName = "id")
    @ManyToOne
    private Sex sex;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kalroOfficer")
    private List<TechnologyTargetCounty> technologyTargetCountyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<UserAccount> userAccountList;

    public Person() {
    }

    public Person(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @XmlTransient
    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public void setTraineeList(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    @XmlTransient
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @XmlTransient
    public List<Trainer> getTrainerList() {
        return trainerList;
    }

    public void setTrainerList(List<Trainer> trainerList) {
        this.trainerList = trainerList;
    }

    @XmlTransient
    public List<SoilFertilityPackage> getSoilFertilityPackageList() {
        return soilFertilityPackageList;
    }

    public void setSoilFertilityPackageList(List<SoilFertilityPackage> soilFertilityPackageList) {
        this.soilFertilityPackageList = soilFertilityPackageList;
    }

    @XmlTransient
    public List<ValidationWorkshops> getValidationWorkshopsList() {
        return validationWorkshopsList;
    }

    public void setValidationWorkshopsList(List<ValidationWorkshops> validationWorkshopsList) {
        this.validationWorkshopsList = validationWorkshopsList;
    }

    @XmlTransient
    public List<ExtensionAndFieldVisitData> getExtensionAndFieldVisitDataList() {
        return extensionAndFieldVisitDataList;
    }

    public void setExtensionAndFieldVisitDataList(List<ExtensionAndFieldVisitData> extensionAndFieldVisitDataList) {
        this.extensionAndFieldVisitDataList = extensionAndFieldVisitDataList;
    }

    @XmlTransient
    public List<Technology> getTechnologyList() {
        return technologyList;
    }

    public void setTechnologyList(List<Technology> technologyList) {
        this.technologyList = technologyList;
    }

    @XmlTransient
    public List<EVoucher> getEVoucherList() {
        return eVoucherList;
    }

    public void setEVoucherList(List<EVoucher> eVoucherList) {
        this.eVoucherList = eVoucherList;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @XmlTransient
    public List<OnFarmTrialsAndDemonstrations> getOnFarmTrialsAndDemonstrationsList() {
        return onFarmTrialsAndDemonstrationsList;
    }

    public void setOnFarmTrialsAndDemonstrationsList(List<OnFarmTrialsAndDemonstrations> onFarmTrialsAndDemonstrationsList) {
        this.onFarmTrialsAndDemonstrationsList = onFarmTrialsAndDemonstrationsList;
    }

    @XmlTransient
    public List<ExtensionMaterialAndGuideline> getExtensionMaterialAndGuidelineList() {
        return extensionMaterialAndGuidelineList;
    }

    public void setExtensionMaterialAndGuidelineList(List<ExtensionMaterialAndGuideline> extensionMaterialAndGuidelineList) {
        this.extensionMaterialAndGuidelineList = extensionMaterialAndGuidelineList;
    }

    @XmlTransient
    public List<DisseminationOfResults> getDisseminationOfResultsList() {
        return disseminationOfResultsList;
    }

    public void setDisseminationOfResultsList(List<DisseminationOfResults> disseminationOfResultsList) {
        this.disseminationOfResultsList = disseminationOfResultsList;
    }

    @XmlTransient
    public List<SampledFarmerData> getSampledFarmerDataList() {
        return sampledFarmerDataList;
    }

    public void setSampledFarmerDataList(List<SampledFarmerData> sampledFarmerDataList) {
        this.sampledFarmerDataList = sampledFarmerDataList;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public DesignationInGroup getDesignationInGroup() {
        return designationInGroup;
    }

    public void setDesignationInGroup(DesignationInGroup designationInGroup) {
        this.designationInGroup = designationInGroup;
    }

    public FarmerGroup getFarmerGroup() {
        return farmerGroup;
    }

    public void setFarmerGroup(FarmerGroup farmerGroup) {
        this.farmerGroup = farmerGroup;
    }

    public FarmerSubGroup getFarmerSubGroup() {
        return farmerSubGroup;
    }

    public void setFarmerSubGroup(FarmerSubGroup farmerSubGroup) {
        this.farmerSubGroup = farmerSubGroup;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @XmlTransient
    public List<TechnologyTargetCounty> getTechnologyTargetCountyList() {
        return technologyTargetCountyList;
    }

    public void setTechnologyTargetCountyList(List<TechnologyTargetCounty> technologyTargetCountyList) {
        this.technologyTargetCountyList = technologyTargetCountyList;
    }

    @XmlTransient
    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.Person[ id=" + id + " ]";
    }
    
}
