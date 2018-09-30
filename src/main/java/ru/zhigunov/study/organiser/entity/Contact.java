package ru.zhigunov.study.organiser.entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {

    @XmlElement(name = "id", required = true)
    private Integer id;

    @XmlElement(name = "FIO")
    private String FIO;

    @XmlElement(name = "jobPost")
    private String jobPost;

    @XmlElement(name = "organisation")
    private String organisation;

    @XmlElement(name = "email")
    private String email;

    @XmlElementWrapper(name="phones")
    @XmlElement(name = "phone")
    private List<String> phones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                ", jobPost='" + jobPost + '\'' +
                ", organisation='" + organisation + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (FIO != null ? !FIO.equals(contact.FIO) : contact.FIO != null) return false;
        if (jobPost != null ? !jobPost.equals(contact.jobPost) : contact.jobPost != null) return false;
        if (organisation != null ? !organisation.equals(contact.organisation) : contact.organisation != null)
            return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        return phones != null ? phones.equals(contact.phones) : contact.phones == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (FIO != null ? FIO.hashCode() : 0);
        result = 31 * result + (jobPost != null ? jobPost.hashCode() : 0);
        result = 31 * result + (organisation != null ? organisation.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        return result;
    }
}
