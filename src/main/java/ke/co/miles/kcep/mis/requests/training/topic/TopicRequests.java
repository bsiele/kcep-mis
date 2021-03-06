/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.training.topic;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Topic;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.TopicDetails;

/**
 *
 * @author siech
 */
@Stateless
public class TopicRequests extends EntityRequests implements TopicRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public short addTopic(TopicDetails topicDetails) throws MilesException {

        if (topicDetails == null) {
            throw new InvalidArgumentException("error_033_01");
        } else if (topicDetails.getTopic() == null) {
            throw new InvalidArgumentException("error_033_02");
        } else if (topicDetails.getTopic().length() > 200) {
            throw new InvalidArgumentException("error_033_03");
        }

        Topic topic;
        setQ(em.createNamedQuery("Topic.findByTopic"));
        q.setParameter("topic", topicDetails.getTopic());
        try {
            topic = (Topic) q.getSingleResult();
        } catch (Exception e) {
            topic = null;
        }
        if (topic != null) {
            throw new InvalidArgumentException("error_033_04");
        }

        topic = new Topic();
        topic.setTopic(topicDetails.getTopic());

        try {
            em.persist(topic);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return topic.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<TopicDetails> retrieveTrainingModules(Integer trainerId) throws MilesException {
        List<Topic> trainingModules = new ArrayList<>();
        setQ(em.createNamedQuery("Topic.findByModuleAndTrainerId"));
        q.setParameter("trainerId", trainerId);
        try {
            trainingModules = q.getResultList();
        } catch (Exception e) {
        }

        return convertTopicsToTopicDetailsList(trainingModules);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TopicDetails> retrieveTrainingModules() throws MilesException {
        List<Topic> trainingModules = new ArrayList<>();
        setQ(em.createNamedQuery("Topic.findByModule"));
        try {
            trainingModules = q.getResultList();
        } catch (Exception e) {
        }

        return convertTopicsToTopicDetailsList(trainingModules);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TopicDetails> retrieveTopics(short moduleId) throws MilesException {
        List<Topic> topics = new ArrayList<>();
        setQ(em.createNamedQuery("Topic.findByModuleId"));
        q.setParameter("moduleId", moduleId);
        try {
            topics = q.getResultList();
        } catch (Exception e) {
        }

        return convertTopicsToTopicDetailsList(topics);
    }

    @Override
    public TopicDetails retrieveTopic(short id) throws MilesException {
        Topic topic;
        setQ(em.createNamedQuery("Topic.findById"));
        q.setParameter("id", id);
        try {
            topic = (Topic) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertTopicToTopicDetails(topic);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editTopic(TopicDetails topicDetails) throws MilesException {

        if (topicDetails == null) {
            throw new InvalidArgumentException("error_033_01");
        } else if (topicDetails.getId() == null) {
            throw new InvalidArgumentException("error_033_05");
        } else if (topicDetails.getTopic() == null) {
            throw new InvalidArgumentException("error_033_02");
        } else if (topicDetails.getTopic().length() > 200) {
            throw new InvalidArgumentException("error_033_03");
        }

        Topic topic;
        setQ(em.createNamedQuery("Topic.findByTopic"));
        q.setParameter("topic", topicDetails.getTopic());
        try {
            topic = (Topic) q.getSingleResult();
        } catch (Exception e) {
            topic = null;
        }
        if (topic != null) {
            if (topic.getId().equals(topicDetails.getId())) {
                throw new InvalidArgumentException("error_033_04");
            }
        }

        topic = em.find(Topic.class, topicDetails.getId());
        topic.setId(topicDetails.getId());
        topic.setTopic(topicDetails.getTopic());

        try {
            em.merge(topic);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeTopic(short id) throws MilesException {
        Topic topic = em.find(Topic.class, id);
        try {
            em.remove(topic);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public TopicDetails convertTopicToTopicDetails(Topic topic) {

        TopicDetails topicDetails = new TopicDetails();
        if (topic == null) {
            return new TopicDetails();
        }
        topicDetails.setTopic(topic.getTopic());
        topicDetails.setId(topic.getId());
        try {
            topicDetails.setModule(convertTopicToTopicDetails(topic.getModule()));
        } catch (Exception e) {
        }

        return topicDetails;

    }

    private List<TopicDetails> convertTopicsToTopicDetailsList(List<Topic> topics) {

        List<TopicDetails> topicDetailsList = new ArrayList<>();
        for (Topic topic : topics) {
            topicDetailsList.add(convertTopicToTopicDetails(topic));
        }

        return topicDetailsList;

    }

//</editor-fold>
}
